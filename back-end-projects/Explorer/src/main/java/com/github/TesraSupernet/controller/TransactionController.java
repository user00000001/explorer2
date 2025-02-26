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


package com.github.TesraSupernet.controller;

import com.github.TesraSupernet.aop.RequestLimit;
import com.github.TesraSupernet.model.common.ResponseBean;
import com.github.TesraSupernet.service.ITransactionService;
import com.github.TesraSupernet.util.Helper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author zhouq
 * @version 1.0
 * @date 2018/3/15
 */
@Validated
@Slf4j
@RestController
@RequestMapping(value = "/v2")
public class TransactionController {

    private final String CLASS_NAME = this.getClass().getSimpleName();

    private final ITransactionService transactionService;

    @Autowired
    public TransactionController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @ApiOperation(value = "Get latest transaction list")
    @GetMapping(value = "/latest-transactions")
    public ResponseBean queryLatestTxs(@RequestParam("count") @Max(50) @Min(1) int count) {

        log.info("###{}.{} begin...", CLASS_NAME, Helper.currentMethod());

        ResponseBean rs = transactionService.queryLatestTxs(count);
        return rs;
    }

    @RequestLimit(count = 120)
    @ApiOperation(value = "Get transaction list by page")
    @GetMapping(value = "/transactions")
    public ResponseBean queryTxsByPage(@RequestParam("page_size") @Min(1) @Max(20) Integer pageSize,
                                       @RequestParam("page_number") @Min(1) Integer pageNumber) {

        log.info("###{}.{} begin...pageSize:{},pageNumber:{}", CLASS_NAME, Helper.currentMethod(), pageSize, pageNumber);

        ResponseBean rs = transactionService.queryTxsByPage(pageNumber, pageSize);
        return rs;
    }

    @ApiOperation(value = "Get latest nontstid transaction list")
    @GetMapping(value = "/latest-nontstid-transactions")
    public ResponseBean queryLatestNontstidTxs(@RequestParam("count") @Max(50) @Min(1) int count) {

        log.info("###{}.{} begin...", CLASS_NAME, Helper.currentMethod());

        ResponseBean rs = transactionService.queryLatestNontstidTxs(count);
        return rs;
    }

    @ApiOperation(value = "Get nontstid transaction list by page")
    @GetMapping(value = "/nontstid-transactions")
    public ResponseBean queryNontstidTxsByPage(@RequestParam("page_size") @Min(1) @Max(20) Integer pageSize,
                                               @RequestParam("page_number") @Min(1) Integer pageNumber) {

        log.info("###{}.{} begin...pageSize:{},pageNumber:{}", CLASS_NAME, Helper.currentMethod(), pageSize, pageNumber);

        ResponseBean rs = transactionService.queryNontstidTxsByPage(pageNumber, pageSize);
        return rs;
    }


    @ApiOperation(value = "Get transaction detail by txhash")
    @GetMapping(value = "/transactions/{tx_hash}")
    public ResponseBean queryTxnByHash(@PathVariable("tx_hash") @Length(min = 64, max = 64, message = "Incorrect transaction hash") String txHash) {

        log.info("###{}.{} begin...txHash:{}", CLASS_NAME, Helper.currentMethod(), txHash);

        ResponseBean rs = transactionService.queryTxDetailByHash(txHash);
        return rs;
    }


}
