/*
 * Copyright (C) 2018 The TesraSupernet Authors
 * This file is part of The TesraSupernet library.
 *
 * The TesraSupernet is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The TesraSupernet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with The TesraSupernet.  If not, see <http://www.gnu.org/licenses/>.
 */


package com.github.TesraSupernet.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.TesraSupernet.mapper.CurrentMapper;
import com.github.TesraSupernet.mapper.TstidTxDetailMapper;
import com.github.TesraSupernet.mapper.TxDetailMapper;
import com.github.TesraSupernet.mapper.TxEventLogMapper;
import com.github.TesraSupernet.model.common.EventTypeEnum;
import com.github.TesraSupernet.model.common.PageResponseBean;
import com.github.TesraSupernet.model.common.ResponseBean;
import com.github.TesraSupernet.model.dto.CurrentDto;
import com.github.TesraSupernet.model.dto.TstidTxDetailDto;
import com.github.TesraSupernet.model.dto.TxDetailDto;
import com.github.TesraSupernet.model.dto.TxEventLogDto;
import com.github.TesraSupernet.service.ITransactionService;
import com.github.TesraSupernet.util.ConstantParam;
import com.github.TesraSupernet.util.ErrorInfo;
import com.github.TesraSupernet.util.Helper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("TransactionService")
public class TransactionServiceImpl implements ITransactionService {

    private final TxDetailMapper txDetailMapper;
    private final CurrentMapper currentMapper;
    private final TstidTxDetailMapper tstidTxDetailMapper;
    private final TxEventLogMapper txEventLogMapper;

    @Autowired
    public TransactionServiceImpl(TxDetailMapper txDetailMapper, CurrentMapper currentMapper, TstidTxDetailMapper tstidTxDetailMapper, TxEventLogMapper txEventLogMapper) {
        this.txDetailMapper = txDetailMapper;
        this.currentMapper = currentMapper;
        this.tstidTxDetailMapper = tstidTxDetailMapper;
        this.txEventLogMapper = txEventLogMapper;
    }


    @Override
    public ResponseBean queryLatestTxs(int count) {

        List<TxEventLogDto> txEventLogDtos = txEventLogMapper.selectTxsByPage(0, count);

        return new ResponseBean(ErrorInfo.SUCCESS.code(), ErrorInfo.SUCCESS.desc(), txEventLogDtos);
    }

    @Override
    public ResponseBean queryTxsByPage(int pageNumber, int pageSize) {

        int start = pageSize * (pageNumber - 1) < 0 ? 0 : pageSize * (pageNumber - 1);

        List<TxEventLogDto> txEventLogDtos = txEventLogMapper.selectTxsByPage(start, pageSize);

        CurrentDto currentDto = currentMapper.selectSummaryInfo();

        PageResponseBean pageResponseBean = new PageResponseBean(txEventLogDtos, currentDto.getTxCount());

        return new ResponseBean(ErrorInfo.SUCCESS.code(), ErrorInfo.SUCCESS.desc(), pageResponseBean);
    }

    @Override
    public ResponseBean queryLatestNontstidTxs(int count) {

        List<TxEventLogDto> txEventLogDtos = txEventLogMapper.selectNontstidTxsByPage(0, count);

        return new ResponseBean(ErrorInfo.SUCCESS.code(), ErrorInfo.SUCCESS.desc(), txEventLogDtos);
    }

    @Override
    public ResponseBean queryNontstidTxsByPage(int pageNumber, int pageSize) {

        int start = pageSize * (pageNumber - 1) < 0 ? 0 : pageSize * (pageNumber - 1);

        List<TxEventLogDto> txEventLogDtos = txEventLogMapper.selectNontstidTxsByPage(start, pageSize);

        CurrentDto currentDto = currentMapper.selectSummaryInfo();

        PageResponseBean pageResponseBean = new PageResponseBean(txEventLogDtos, currentDto.getTxCount() - currentDto.getTstidTxCount());

        return new ResponseBean(ErrorInfo.SUCCESS.code(), ErrorInfo.SUCCESS.desc(), pageResponseBean);
    }

    @Override
    public ResponseBean queryTxDetailByHash(String txHash) {

        TxDetailDto txDetailDto = txDetailMapper.selectTxByHash(txHash);
        if (Helper.isEmptyOrNull(txDetailDto)) {
            return new ResponseBean(ErrorInfo.NOT_FOUND.code(), ErrorInfo.NOT_FOUND.desc(), false);
        }

        JSONObject detailObj = new JSONObject();
        int eventType = txDetailDto.getEventType();
        //转账or权限交易，获取转账详情
        if (EventTypeEnum.Transfer.getType() == eventType || EventTypeEnum.Auth.getType() == eventType) {

            List<TxDetailDto> txDetailDtos = txDetailMapper.selectTransferTxDetailByHash(txHash);

            txDetailDtos.forEach(item -> {
                //TSG转换好精度给前端
                String assetName = item.getAssetName();
                if (ConstantParam.TSG.equals(assetName)) {
                    item.setAmount(item.getAmount().divide(ConstantParam.TSG_TOTAL));
                }
            });
            detailObj.put("transfers", txDetailDtos);
        } else if (EventTypeEnum.Tstid.getType() == eventType) {
            //TSTID交易获取TSTID动作详情
            TstidTxDetailDto tstidTxDetailDto = tstidTxDetailMapper.selectOneByTxHash(txHash);

            String tstIdDes = Helper.templateTstIdOperation(tstidTxDetailDto.getDescription());

            detailObj.put("tstid", tstidTxDetailDto.getTstid());
            detailObj.put("description", tstIdDes);
        }
        txDetailDto.setDetail(detailObj);

        return new ResponseBean(ErrorInfo.SUCCESS.code(), ErrorInfo.SUCCESS.desc(), txDetailDto);
    }


}
