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


package com.github.TesraSupernet.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Service("ParamsConfig")
public class ParamsConfig {

    @Value("${masternode.restful.url}")
    public String MASTERNODE_RESTFUL_URL;

    @Value("${blockchain.node.count}")
    public Integer BLOCKCHAIN_NODE_COUNT;

    @Value("${blockchain.max.tps}")
    public Integer BLOCKCHAIN_MAX_TPS;

    @Value("${tsg.second.generate}")
    public BigDecimal TSG_SECOND_GENERATE;

    @Value("${reqlimit.expire.millisecond}")
    public Integer REQLIMIT_EXPIRE_MILLISECOND;

    @Value("${oep5.dragon.contractHash}")
    public String OEP5_DRAGON_CONTRACTHASH;

    @Value("${dappbind.contracthash}")
    public String DAPPBIND_CONTRACTHASH;

    @Value("${dapp.reward.percentage}")
    public Integer DAPP_REWARD_PERCENTAGE;

    @Value("${node.reward.percentage}")
    public Integer NODE_REWARD_PERCENTAGE;

    @Value("${balanceservice.host}")
    public String BALANCESERVICE_HOST;

    @Value("${querybalance.mode}")
    public Integer QUERYBALANCE_MODE;

    @Value("${config.newStakingRoundBlockCount}")
    public Long newStakingRoundBlockCount;

    @Value("#{'${config.hosts}'.split(',')}")
    private List<String> hosts = new ArrayList<>();

    public interface Field {

        String maxStakingChangeCount = "maxStakingChangeCount";

    }

}