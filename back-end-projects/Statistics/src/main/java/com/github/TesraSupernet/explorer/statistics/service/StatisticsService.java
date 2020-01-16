/*
 * Copyright (C) 2019-2020 The TesraSupernet Authors
 * This file is part of The TesraSupernet library.
 * The TesraSupernet is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * The TesraSupernet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License
 * along with The TesraSupernet.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.TesraSupernet.explorer.statistics.service;

import com.alibaba.fastjson.JSON;
import com.github.TesraSupernet.explorer.statistics.common.ParamsConfig;
import com.github.TesraSupernet.explorer.statistics.common.Constants;
import com.github.TesraSupernet.explorer.statistics.mapper.*;
import com.github.TesraSupernet.explorer.statistics.model.AddressDailySummary;
import com.github.TesraSupernet.explorer.statistics.model.Contract;
import com.github.TesraSupernet.explorer.statistics.model.ContractDailySummary;
import com.github.TesraSupernet.explorer.statistics.model.DailySummary;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@NoArgsConstructor
@Service("StatisticsService")
public class StatisticsService {
    private BlockMapper blockMapper;
    private TxDetailTmpMapper txDetailTmpMapper;
    private ContractMapper contractMapper;
    private TxDetailDailyMapper txDetailDailyMapper;
    private DailySummaryMapper dailySummaryMapper;
    private TstidTxDetailMapper tstidTxDetailMapper;
    private AddressDailySummaryMapper addrDailySummaryMapper;
    private ContractDailySummaryMapper contractDailySummaryMapper;
    private ParamsConfig paramsConfig;


    @Autowired
    public StatisticsService(BlockMapper blockMapper, TxDetailTmpMapper txDetailTmpMapper,
                             ContractMapper contractMapper, TxDetailDailyMapper txDetailDailyMapper,
                             DailySummaryMapper dailySummaryMapper, TstidTxDetailMapper tstidTxDetailMapper,
                             AddressDailySummaryMapper addrDailySummaryMapper, ContractDailySummaryMapper contractDailySummaryMapper,
                             ParamsConfig paramsConfig) {
        this.blockMapper = blockMapper;
        this.txDetailTmpMapper = txDetailTmpMapper;
        this.contractMapper = contractMapper;
        this.txDetailDailyMapper = txDetailDailyMapper;
        this.dailySummaryMapper = dailySummaryMapper;
        this.tstidTxDetailMapper = tstidTxDetailMapper;
        this.addrDailySummaryMapper = addrDailySummaryMapper;
        this.contractDailySummaryMapper = contractDailySummaryMapper;
        this.paramsConfig = paramsConfig;
    }

    public void updateDailySummary() {
        try {
            int dailySumTaskBeginTime = getDailySumTaskBeginTime();
            log.info("Current update daily summary task begin time: {}", dailySumTaskBeginTime);
            int currBlockTime = getCurrBlockTime();
            log.info("Current block time: {}", currBlockTime);
            while (currBlockTime > dailySumTaskBeginTime + Constants.ONE_DAY_IN_SEC) {
                cleanTxDetailTmpTbl();
                updateTxDetailTmp(dailySumTaskBeginTime);
                Map<String, Integer> addrCountMap = updateAddrSumTbl(dailySumTaskBeginTime);
                updateDailySumTbl(dailySumTaskBeginTime, addrCountMap);
                dailySumTaskBeginTime += Constants.ONE_DAY_IN_SEC;
            }
            log.info("Current update daily summary task end time: {}", dailySummaryMapper.selectMaxTime());
        } catch (Exception e) {
            log.error("An error occur: ", e);
        }
    }

    public void updateApprovedContractInfo() {
        try {
            int currDailySumEndTime = getDailySumTblMaxTime();
            log.info("Current max time in daily summary table: {}", currDailySumEndTime);
            int taskEndTime = currDailySumEndTime + Constants.ONE_DAY_IN_SEC;
            cleanTxDetailDailyTbl(taskEndTime);
            updateApprovedContract();
        } catch (Exception e) {
            log.error("An error occur: ", e);
        }
    }

    private void cleanTxDetailDailyTbl(int endTime) {
        if (txDetailDailyMapper.selectiveByEndTime(endTime) != 0) {
            int result = txDetailDailyMapper.deleteByEndTime(endTime);
            log.info("Delete {} data in tx detail daily table before: {}", result, endTime);
        }
    }

    private void cleanTxDetailTmpTbl() {
        int result = txDetailTmpMapper.deleteAll();
        log.info("Delete tx detail in tmp table: {}", result);
    }

    private void updateTxDetailTmp(int beginTime) {
        int endTime = beginTime + Constants.ONE_DAY_IN_SEC;
        int result = txDetailTmpMapper.InsertSelectiveFromDetailTable(beginTime, endTime);
        log.info("Insert data from tx detail table: {}", result);
    }

    private int getCurrBlockTime() {
        return blockMapper.selectBlockMaxTime();
    }

    private int getDailySumTblMaxTime() {
        Integer tblMaxTime = dailySummaryMapper.selectMaxTime();
        if (tblMaxTime == null) {
            tblMaxTime = Constants.GENESIS_TIME;
        }
        return tblMaxTime;
    }

    private int getDailySumTaskBeginTime() {
        int summaryTaskBeginTime = getDailySumTblMaxTime();
        if (summaryTaskBeginTime != Constants.GENESIS_TIME) {
            summaryTaskBeginTime += Constants.ONE_DAY_IN_SEC;
        }
        return summaryTaskBeginTime;
    }

    private int getDailyTxSum() {
        return txDetailTmpMapper.selectTxCountInOneDay();
    }

    private void updateDailySumTbl(Integer dailySumTaskBeginTime, Map<String, Integer> addrCountMap) {

        int dailyBlockSum = getDailyBlockSum(dailySumTaskBeginTime);
        int dailyTstIdSum = getDailyTstIdSum(dailySumTaskBeginTime);
        int dailyActiveTstIdSum = getDailyActiveTstIdSum(dailySumTaskBeginTime);

        DailySummary dailySummary = DailySummary.builder()
                .time(dailySumTaskBeginTime)
                .blockCount(dailyBlockSum)
                .txCount(getDailyTxSum())
                .activeTstidCount(dailyActiveTstIdSum)
                .newTstidCount(dailyTstIdSum)
                .tstSum(getDailyTstSum())
                .tsgSum(getDailyTsgSum())
                .activeAddressCount(addrCountMap.get("dailyActiveAddrCount"))
                .newAddressCount(addrCountMap.get("dailyNewAddrCount"))
                .build();
        dailySummaryMapper.insert(dailySummary);
    }

    private BigDecimal getDailyTstSum() {
        BigDecimal dailyTstAmount = txDetailTmpMapper.selectTstAmountInOneDay();
        return dailyTstAmount == null ? new BigDecimal(0) : dailyTstAmount;
    }

    private BigDecimal getDailyTsgSum() {
        BigDecimal dailyTsgAmount = txDetailTmpMapper.selectTsgAmountInOneDay();
        return dailyTsgAmount == null ? new BigDecimal(0) : dailyTsgAmount.divide(Constants.TSG_TOTAL, 9, RoundingMode.HALF_DOWN);
    }

    private BigDecimal getDailyTstSum(String contractHash) {
        BigDecimal tstCount = txDetailDailyMapper.selectContractAssetAmount(contractHash, Constants.TST);
        return tstCount == null ? new BigDecimal(0) : tstCount;
    }

    private BigDecimal getOneDayTstSum(String contractHash) {
        BigDecimal tstCount = txDetailTmpMapper.selectContractAssetAmount(contractHash, Constants.TST);
        return tstCount == null ? new BigDecimal(0) : tstCount;
    }

    private BigDecimal getOneDayTsgSum(String contractHash) {
        BigDecimal tsgCount = txDetailTmpMapper.selectContractAssetAmount(contractHash, Constants.TSG);
        return tsgCount == null ? new BigDecimal(0) : tsgCount.divide(Constants.TSG_TOTAL, 9, RoundingMode.HALF_DOWN);
    }


    private BigDecimal getDailyTsgSum(String contractHash) {
        BigDecimal tsgCount = txDetailDailyMapper.selectContractAssetAmount(contractHash, Constants.TSG);
        return tsgCount == null ? new BigDecimal(0) : tsgCount.divide(Constants.TSG_TOTAL, 9, RoundingMode.HALF_DOWN);
    }

    private int getDailyTxSum(String contractHash) {
        return txDetailTmpMapper.selectContractTxSum(contractHash);
    }

    private int getDailyTstIdSum(int beginTime) {
        return tstidTxDetailMapper.selectTstIdCountInOneDay(beginTime, beginTime + Constants.ONE_DAY_IN_SEC, "Register%");
    }

    private int getDailyBlockSum(int beginTime) {
        return blockMapper.selectBlockCountInOneDay(beginTime, beginTime + Constants.ONE_DAY_IN_SEC);
    }

    private int getDailyActiveTstIdSum(int beginTime) {
        return tstidTxDetailMapper.selectActiveTstIdCountInOneDay(beginTime, beginTime + Constants.ONE_DAY_IN_SEC);
    }

    private ContractDailySummary getContractDailySummary(String contractHash) {
        ContractDailySummary contractDailySummary = contractDailySummaryMapper.selectContractSummary(contractHash);
        if (contractDailySummary == null) {
            contractDailySummary = ContractDailySummary.builder()
                    .txCount(0)
                    .newAddressCount(0)
                    .tsgSum(Constants.ZERO)
                    .tstSum(Constants.ZERO)
                    .build();
        }
        return contractDailySummary;
    }

    private List<String> getAddrListFromTxDetailTbl4Dapp(String contractHash) {
        return txDetailDailyMapper.selectContractAddr4Dapp(contractHash);
    }

    private List<String> getAddrListFromTxDetailTbl(String contractHash) {
        return txDetailDailyMapper.selectContractAddr(contractHash);
    }

    private List<String> getAddrListFromAddrDailySumTbl(String contractHash) {
        return addrDailySummaryMapper.selectDistinctAddressByContract(contractHash);
    }

    private int getDailyContractNewAddrCount(String contractHash, int dappStoreFlag) {
        List<String> contractAddrList = new ArrayList<>();
        //dapp类型合约,根据from_address+payer计算地址
        if (dappStoreFlag == 1) {
            contractAddrList = getAddrListFromTxDetailTbl4Dapp(contractHash);
        } else {
            //其他类型合约,根据from_address+to_address计算地址
            contractAddrList = getAddrListFromTxDetailTbl(contractHash);
        }
        List<String> fullAddrList = getAddrListFromAddrDailySumTbl(contractHash);
        contractAddrList.removeAll(fullAddrList);
        return contractAddrList.size();
    }

    private String getOepTokenSum(String contractHash, String type) {
        List<Map> oepTokenSum;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("contractHash", contractHash);
        if (type.equalsIgnoreCase("oep5")) {
            paramMap.put("oep5", "oep5");
            oepTokenSum = txDetailDailyMapper.selectContractTokenAllSum(paramMap);
            if (!isEmptyOrNull(oepTokenSum) && oepTokenSum.size() != 0) {
                Map oep8Map = oepTokenSum.get(0);
                if (!isEmptyOrNull(oep8Map)) {
                    oep8Map.put("asset_name", ((String) oep8Map.get("asset_name")).split(":")[0]);
                }
            }
        } else {
            oepTokenSum = txDetailDailyMapper.selectContractTokenAllSum(paramMap);
        }
        oepTokenSum = oepTokenSum == null ? new ArrayList<>() : oepTokenSum;
        return JSON.toJSONString(oepTokenSum);
    }

    private Integer getDailyTxCount(String contractHash) {
        return txDetailDailyMapper.selectTxCount(contractHash);
    }

    private void updateApprovedContract() {
        List<Contract> contractList = contractMapper.selectAllApprovedContract();
        for (Contract contract : contractList) {
            String type = contract.getType();
            String contractHash = contract.getContractHash();
            int dappStoreFlag = contract.getDappstoreFlag();
            log.info("Staring handle {} contract {} named {} which dApp store flag is {}", type, contractHash, contract.getName(), dappStoreFlag);

            ContractDailySummary contractDailySummary = getContractDailySummary(contractHash);
            contract.setTxCount(contractDailySummary.getTxCount() + getDailyTxCount(contractHash));
            contract.setTstSum(contractDailySummary.getTstSum().add(getDailyTstSum(contractHash)));
            contract.setTsgSum(contractDailySummary.getTsgSum().add(getDailyTsgSum(contractHash)));
            contract.setTokenSum(getOepTokenSum(contractHash, type));
            contract.setAddressCount(contractDailySummary.getNewAddressCount() + getDailyContractNewAddrCount(contractHash, dappStoreFlag));
            contractMapper.updateByPrimaryKeySelective(contract);
        }
    }

    private static Boolean isEmptyOrNull(Object... params) {
        if (params != null) {
            for (Object val : params) {
                if ("".equals(val) || val == null) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    private List<AddressDailySummary> updateDailyContractInfoAndContractNewAddress(Integer beginTime) {
        List<Contract> contractList;
        //测试网只更新审核后的合约
        if (paramsConfig.getIsTestNet()) {
            contractList = contractMapper.selectAllApprovedContract();
        } else {
            contractList = contractMapper.selectAll();
        }
        if (contractList.isEmpty()) {
            return new ArrayList<>();
        }
        List<ContractDailySummary> contractSummaryList = new ArrayList<>();
        List<AddressDailySummary> contractAddrSummaryList = new ArrayList<>();
        //记录每个合约当天的统计数据，并将每个合约的新地址插入地址汇总表
        for (Contract contract : contractList) {
            String contractHash = contract.getContractHash();
            BigDecimal dailyTstAmount = getOneDayTstSum(contractHash);
            BigDecimal dailyTsgAmount = getOneDayTsgSum(contractHash);
            int dailyTxCount = getDailyTxSum(contractHash);

            List<String> dailyActiveAddress;
            //dapp类型合约,根据from_address+payer计算地址
            if (contract.getDappstoreFlag() == 1) {
                dailyActiveAddress = txDetailTmpMapper.selectContractAddr4Dapp(contractHash);
            } else {
                //其他类型合约,根据from_address+to_address计算地址
                dailyActiveAddress = txDetailTmpMapper.selectContractAddr(contractHash);
            }
            int dailyActiveAddrCount = dailyActiveAddress.size();

            List<String> allAddrList = addrDailySummaryMapper.selectDistinctAddressByContract(contractHash);
            dailyActiveAddress.removeAll(allAddrList);
            int dailyNewAddrCount = dailyActiveAddress.size();

            ContractDailySummary contractSummary = ContractDailySummary.builder()
                    .time(beginTime)
                    .dappName(contract.getDappName())
                    .contractHash(contractHash)
                    .tstSum(dailyTstAmount)
                    .tsgSum(dailyTsgAmount)
                    .txCount(dailyTxCount)
                    .activeAddressCount(dailyActiveAddrCount)
                    .newAddressCount(dailyNewAddrCount)
                    .build();
            contractSummaryList.add(contractSummary);
            for (String address : dailyActiveAddress) {
                AddressDailySummary addressSummary = AddressDailySummary.builder()
                        .time(beginTime)
                        .contractHash(contractHash)
                        .address(address)
                        .build();
                contractAddrSummaryList.add(addressSummary);
            }
        }
        int insertResult = contractDailySummaryMapper.batchInsertSelective(contractSummaryList);
        log.info("Batch insert contract in contract daily summary table: {}", insertResult);
        return contractAddrSummaryList;
    }

    private Map<String, Integer> updateAddrSumTbl(Integer dailySumTaskBeginTime) {

        Map<String, Integer> map = new HashMap<>();

        List<String> dailyAddrRecords = txDetailTmpMapper.selectAddressInOneDay();
        map.put("dailyActiveAddrCount", dailyAddrRecords.size());

        List<String> addressRecords = addrDailySummaryMapper.selectDistinctAddressByContract(Constants.ADDR_DAILY_SUMMARY_NATIVETYPE);
        dailyAddrRecords.removeAll(addressRecords);

        map.put("dailyNewAddrCount", dailyAddrRecords.size());

        List<AddressDailySummary> contractAddrSumList = updateDailyContractInfoAndContractNewAddress(dailySumTaskBeginTime);
        List<AddressDailySummary> addrSummaryList = new ArrayList<>();
        for (String address : dailyAddrRecords) {
            AddressDailySummary addrDailySummary = AddressDailySummary.builder()
                    .time(dailySumTaskBeginTime)
                    .contractHash(Constants.ADDR_DAILY_SUMMARY_NATIVETYPE)
                    .address(address)
                    .build();
            addrSummaryList.add(addrDailySummary);
        }

        addrSummaryList.addAll(contractAddrSumList);
        if (!addrSummaryList.isEmpty()) {
            int insertResult = addrDailySummaryMapper.batchInsertSelective(addrSummaryList);
            log.info("Batch insert address in address daily summary table: {}", insertResult);
        }

        return map;
    }
}

