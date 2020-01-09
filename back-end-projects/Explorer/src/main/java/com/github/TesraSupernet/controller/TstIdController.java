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
import com.github.TesraSupernet.service.impl.TstIdServiceImpl;
import com.github.TesraSupernet.util.Helper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 * @author zhouq
 * @version 1.0
 * @date 2018/3/15
 */
@Validated
@RestController
@RequestMapping(value = "/v2")
@Slf4j
public class TstIdController {

    private final String CLASS_NAME = this.getClass().getSimpleName();

    private final TstIdServiceImpl tstIdService;

    @Autowired
    public TstIdController(TstIdServiceImpl tstIdService) {
        this.tstIdService = tstIdService;
    }


    @ApiOperation(value = "Get latest TST ID transaction list")
    @GetMapping(value = "/latest-tstids")
    public ResponseBean queryLatestTstIdTxs(@RequestParam("count") @Max(50) @Min(1) int count) {

        log.info("###{}.{} begin...", CLASS_NAME, Helper.currentMethod());

        ResponseBean rs = tstIdService.queryLatestTstIdTxs(count);
        return rs;
    }


    @ApiOperation(value = "Get TST ID transaction list by page")
    @GetMapping(value = "/tstids")
    public ResponseBean queryTstIdTxsByPage(@RequestParam("page_size") @Max(20) @Min(1) int pageSize,
                                            @RequestParam("page_number") @Min(1) int pageNumber) {

        log.info("###{}.{} begin...", CLASS_NAME, Helper.currentMethod());

        ResponseBean rs = tstIdService.queryTstidTxsByPage(pageSize, pageNumber);
        return rs;
    }

    @RequestLimit(count = 120)
    @ApiOperation(value = "Get TST ID transaction list by page")
    @GetMapping(value = "/tstids/{tstid}/transactions")
    public ResponseBean queryTstIdTxsByTstid(@PathVariable("tstid") @Pattern(regexp = "did:tst:A[A-Za-z0-9]{33}", message = "Incorrect TST ID format") String tstid,
                                             @RequestParam("page_size") @Max(20) @Min(1) int pageSize,
                                             @RequestParam("page_number") @Min(1) int pageNumber) {

        log.info("####{}.{} begin...tstid:{}", CLASS_NAME, Helper.currentMethod(), tstid);

        ResponseBean rs = tstIdService.queryTstIdTxsByTstid(tstid, pageSize, pageNumber);
        return rs;
    }

    @ApiOperation(value = "Get TST ID Ddo by page")
    @GetMapping(value = "/tstids/{tstid}/ddo")
    public ResponseBean queryTstIdDdo(@PathVariable("tstid") @Pattern(regexp = "did:tst:A[A-Za-z0-9]{33}", message = "Incorrect TST ID format") String tstid) {

        log.info("####{}.{} begin...tstid:{}", CLASS_NAME, Helper.currentMethod(), tstid);

        ResponseBean rs = tstIdService.queryTstidDdo(tstid);
        return rs;
    }


}
