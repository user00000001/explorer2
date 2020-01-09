/*
 * Copyright (C) 2019-2020 The TesraSupernet Authors
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


package com.github.TesraSupernet.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("ParamsConfig")
public class ParamsConfig {


    /**
     * tesra blockchain restful url
     */
    @Value("${masternode.restful.url}")
    public String MASTERNODE_RESTFUL_URL;

    /**
     * the amount of the tesra blockchain nodes in properties
     */
    @Value("${node.count}")
    public int NODE_COUNT;

    /**
     * the interval for waiting block generation
     */
    @Value("${block.interval}")
    public int BLOCK_INTERVAL;

    /**
     * each node fault tolerance maximum time.
     */
    @Value("${node.interruptTime.max}")
    public int NODE_INTERRUPTTIME_MAX;

    /**
     * the maximum time of each node for waiting for generating block
     */
    @Value("${node.waitForBlockTime.max}")
    public int NODE_WAITFORBLOCKTIME_MAX;

    /**
     * tesra blockchain TST asset smartcontract codehash
     */
    @Value("${tst.contractHash}")
    public String TST_CONTRACTHASH;

    /**
     * tesra blockchain TSG asset smartcontract codehash
     */
    @Value("${tsg.contractHash}")
    public String TSG_CONTRACTHASH;

    /**
     * tesra blockchain tstId smartcontract codehash
     */
    @Value("${tstId.contractHash}")
    public String TSTID_CONTRACTHASH;

    /**
     * tesra blockchain record smartcontract codehash
     */
    @Value("${claimRecord.contractHash}")
    public String CLAIMRECORD_CONTRACTHASH;

    /**
     * tesra blockchainauth smartcontract codehash
     */
    @Value("${auth.contractHash}")
    public String AUTH_CONTRACTHASH;

    @Value("${pax.contractHash}")
    public String PAX_CONTRACTHASH;

    @Value("${threadPoolSize.max}")
    public int THREADPOOLSIZE_MAX;

    @Value("${threadPoolSize.core}")
    public int THREADPOOLSIZE_CORE;

    @Value("${threadPoolSize.queue}")
    public int THREADPOOLSIZE_QUEUE;

    @Value("${threadPoolSize.keepalive}")
    public int THREADPOOLSIZE_KEEPALIVE_SECOND;


    @Value("${batchInsert.blockCount}")
    public int BATCHINSERT_BLOCK_COUNT;

    @Value("${batchInsert.sqlCount}")
    public int BATCHINSERT_SQL_COUNT;

}