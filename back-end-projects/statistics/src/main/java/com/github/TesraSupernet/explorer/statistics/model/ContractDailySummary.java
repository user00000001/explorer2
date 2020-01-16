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

package com.github.TesraSupernet.explorer.statistics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_contract_daily_summary")
public class ContractDailySummary {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 当天的UTC0点时间戳
     */
    private Integer time;

    /**
     * 合约hash值
     */
    @Column(name = "contract_hash")
    private String contractHash;

    /**
     * 此合约当天的交易数量
     */
    @Column(name = "tx_count")
    private Integer txCount;

    /**
     * 此合约当天的tst流通量
     */
    @Column(name = "tst_sum")
    private BigDecimal tstSum;

    /**
     * 此合约当天的tsg流通量
     */
    @Column(name = "tsg_sum")
    private BigDecimal tsgSum;

    /**
     * 此合约当天的活跃地址数
     */
    @Column(name = "active_address_count")
    private Integer activeAddressCount;

    /**
     * 此合约当天的新地址数
     */
    @Column(name = "new_address_count")
    private Integer newAddressCount;

    /**
     * 合约所属dapp名称
     */
    @Column(name = "dapp_name")
    private String dappName;

}