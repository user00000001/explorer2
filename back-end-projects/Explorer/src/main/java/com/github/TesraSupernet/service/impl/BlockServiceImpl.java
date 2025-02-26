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

import com.github.TesraSupernet.mapper.BlockMapper;
import com.github.TesraSupernet.mapper.CurrentMapper;
import com.github.TesraSupernet.mapper.TxEventLogMapper;
import com.github.TesraSupernet.model.common.PageResponseBean;
import com.github.TesraSupernet.model.common.ResponseBean;
import com.github.TesraSupernet.model.dto.BlockDto;
import com.github.TesraSupernet.model.dto.CurrentDto;
import com.github.TesraSupernet.model.dto.TxBasicDto;
import com.github.TesraSupernet.service.IBlockService;
import com.github.TesraSupernet.util.ErrorInfo;
import com.github.TesraSupernet.util.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("BlockService")
public class BlockServiceImpl implements IBlockService {

    private final BlockMapper blockMapper;
    private final TxEventLogMapper txEventLogMapper;
    private final CurrentMapper currentMapper;

    @Autowired
    public BlockServiceImpl(BlockMapper blockMapper, TxEventLogMapper txEventLogMapper, CurrentMapper currentMapper) {
        this.blockMapper = blockMapper;
        this.txEventLogMapper = txEventLogMapper;
        this.currentMapper = currentMapper;
    }


    @Override
    public ResponseBean queryLatestBlocks(int amount) {
        List<BlockDto> blockDtos = blockMapper.selectBlocksByPage(0, amount);
        return new ResponseBean(ErrorInfo.SUCCESS.code(), ErrorInfo.SUCCESS.desc(), blockDtos);
    }

    @Override
    public ResponseBean queryBlocksByPage(int pageSize, int pageNumber) {

        int start = pageSize * (pageNumber - 1) < 0 ? 0 : pageSize * (pageNumber - 1);
        List<BlockDto> blockDtos = blockMapper.selectBlocksByPage(start, pageSize);

        CurrentDto currentDto = currentMapper.selectSummaryInfo();
        //区块高度从0开始，区块个数=区块高度+1
        PageResponseBean pageResponseBean = new PageResponseBean(blockDtos, currentDto.getBlockHeight() + 1);

        return new ResponseBean(ErrorInfo.SUCCESS.code(), ErrorInfo.SUCCESS.desc(), pageResponseBean);
    }

    @Override
    public ResponseBean queryBlockByHeight(int blockHeight) {

        BlockDto blockDto = blockMapper.selectOneByHeight(blockHeight);
        if (Helper.isEmptyOrNull(blockDto)) {
            return new ResponseBean(ErrorInfo.NOT_FOUND.code(), ErrorInfo.NOT_FOUND.desc(), false);
        }
        List<TxBasicDto> txBasicDtos = txEventLogMapper.selectTxsByBlockHeight(blockHeight);
        if (Helper.isNotEmptyAndNull(txBasicDtos)) {
            blockDto.setTxs(txBasicDtos);
        }

        return new ResponseBean(ErrorInfo.SUCCESS.code(), ErrorInfo.SUCCESS.desc(), blockDto);
    }

    @Override
    public ResponseBean queryBlockByHash(String blockHash) {

        BlockDto blockDto = blockMapper.selectOneByHash(blockHash);
        if (Helper.isEmptyOrNull(blockDto)) {
            return new ResponseBean(ErrorInfo.NOT_FOUND.code(), ErrorInfo.NOT_FOUND.desc(), false);
        }
        int blockHeight = blockDto.getBlockHeight();

        List<TxBasicDto> txBasicDtos = txEventLogMapper.selectTxsByBlockHeight(blockHeight);
        if (Helper.isNotEmptyAndNull(txBasicDtos)) {
            blockDto.setTxs(txBasicDtos);
        }

        return new ResponseBean(ErrorInfo.SUCCESS.code(), ErrorInfo.SUCCESS.desc(), blockDto);
    }

    @Override
    public ResponseBean queryBlockGenerateTime(int count) {

        List<Map> dataList = blockMapper.selectHeightAndTime(count + 1);
        List<Map> rsList = new ArrayList<>();
        for (int i = 0; i < dataList.size() - 1; i++) {
            int time = (Integer) dataList.get(i).get("blockTime") - (Integer) dataList.get(i + 1).get("blockTime");
            Map<String, Object> temp = new HashMap<>();
            temp.put("block_height", dataList.get(i).get("blockHeight"));
            temp.put("generate_time", time);
            rsList.add(temp);
        }
        return new ResponseBean(ErrorInfo.SUCCESS.code(), ErrorInfo.SUCCESS.desc(), rsList);
    }


}
