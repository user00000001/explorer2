package com.github.TesraSupernet.service.impl;

import com.github.TesraSupernet.config.ParamsConfig;
import com.github.TesraSupernet.mapper.*;
import com.github.TesraSupernet.model.common.PageResponseBean;
import com.github.TesraSupernet.model.common.ResponseBean;
import com.github.TesraSupernet.model.dto.ContractDailySummaryDto;
import com.github.TesraSupernet.model.dto.CurrentDto;
import com.github.TesraSupernet.model.dto.DailySummaryDto;
import com.github.TesraSupernet.service.ISummaryService;
import com.github.TesraSupernet.util.ConstantParam;
import com.github.TesraSupernet.util.ErrorInfo;
import com.github.TesraSupernet.util.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("SummaryService")
public class SummaryServiceImpl implements ISummaryService {

    private final ParamsConfig paramsConfig;
    private final TxEventLogMapper txEventLogMapper;
    private final DailySummaryMapper dailySummaryMapper;
    private final ContractDailySummaryMapper contractDailySummaryMapper;
    private final CurrentMapper currentMapper;
    private final AddressDailySummaryMapper addressDailySummaryMapper;

    @Autowired
    public SummaryServiceImpl(ParamsConfig paramsConfig, TxEventLogMapper txEventLogMapper, DailySummaryMapper dailySummaryMapper, ContractDailySummaryMapper contractDailySummaryMapper, CurrentMapper currentMapper, AddressDailySummaryMapper addressDailySummaryMapper) {
        this.paramsConfig = paramsConfig;
        this.txEventLogMapper = txEventLogMapper;
        this.dailySummaryMapper = dailySummaryMapper;
        this.contractDailySummaryMapper = contractDailySummaryMapper;
        this.currentMapper = currentMapper;
        this.addressDailySummaryMapper = addressDailySummaryMapper;
    }


    @Override
    public ResponseBean getBlockChainLatestInfo() {

        CurrentDto currentDto = currentMapper.selectSummaryInfo();
        currentDto.setNodeCount(paramsConfig.BLOCKCHAIN_NODE_COUNT);

        Integer addressCount = addressDailySummaryMapper.selectAllAddressCount(ConstantParam.ADDR_DAILY_SUMMARY_TSTTSG_CONTRACTHASH);
        currentDto.setAddressCount(addressCount);

        return new ResponseBean(ErrorInfo.SUCCESS.code(), ErrorInfo.SUCCESS.desc(), currentDto);

    }

    @Override
    public ResponseBean getBlockChainTps() {

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("current_tps", calcTps());
        resultMap.put("max_tps", paramsConfig.BLOCKCHAIN_MAX_TPS);

        return new ResponseBean(ErrorInfo.SUCCESS.code(), ErrorInfo.SUCCESS.desc(), resultMap);
    }

    /**
     * 计算当前tps
     *
     * @return
     */
    private String calcTps() {
        Long now = System.currentTimeMillis() / 1000;
        Integer txPerMin = txEventLogMapper.queryTxCount(now - 60, now);
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format((double) (txPerMin) / 60);
    }


    @Override
    public ResponseBean getBlockChainDailySummary(Long startTime, Long endTime) {

        List<DailySummaryDto> dailySummaryDtos = dailySummaryMapper.selectSummaryByTime(startTime, endTime);
        if (dailySummaryDtos.size() > 0) {
            Map<String, BigDecimal> addrAndTstidCountMap = dailySummaryMapper.selectAddrAndTstIdTotal(startTime);
            if (Helper.isEmptyOrNull(addrAndTstidCountMap)) {
                dailySummaryDtos.get(0).setTstidTotal(0);
                dailySummaryDtos.get(0).setAddressTotal(0);
            } else {
                dailySummaryDtos.get(0).setAddressTotal(addrAndTstidCountMap.get("addressTotal").intValue());
                dailySummaryDtos.get(0).setTstidTotal(addrAndTstidCountMap.get("tstidTotal").intValue());
            }

            for (int i = 1; i < dailySummaryDtos.size(); i++) {
                DailySummaryDto dailySummaryDto = dailySummaryDtos.get(i);
                dailySummaryDto.setAddressTotal(dailySummaryDtos.get(i - 1).getAddressTotal() + dailySummaryDto.getNewAddressCount());
                dailySummaryDto.setTstidTotal(dailySummaryDtos.get(i - 1).getTstidTotal() + dailySummaryDto.getNewTstidCount());
            }
        }

        PageResponseBean pageResponseBean = new PageResponseBean(dailySummaryDtos, dailySummaryDtos.size());

        return new ResponseBean(ErrorInfo.SUCCESS.code(), ErrorInfo.SUCCESS.desc(), pageResponseBean);
    }


    @Override
    public ResponseBean getContractDailySummary(String contractHash, Long startTime, Long endTime) {

        List<ContractDailySummaryDto> contractDailySummaryDtos = contractDailySummaryMapper.selectDailySummaryByContractHash(contractHash, startTime, endTime);

        PageResponseBean pageResponseBean = new PageResponseBean(contractDailySummaryDtos, contractDailySummaryDtos.size());

        return new ResponseBean(ErrorInfo.SUCCESS.code(), ErrorInfo.SUCCESS.desc(), pageResponseBean);
    }
}
