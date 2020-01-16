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

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "tbl_dapp_daily_summary")
public class DappDailySummary {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 当天的UTC0点时间戳
     */
    private Integer time;

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

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取当天的UTC0点时间戳
     *
     * @return time - 当天的UTC0点时间戳
     */
    public Integer getTime() {
        return time;
    }

    /**
     * 设置当天的UTC0点时间戳
     *
     * @param time 当天的UTC0点时间戳
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * 获取此合约当天的交易数量
     *
     * @return tx_count - 此合约当天的交易数量
     */
    public Integer getTxCount() {
        return txCount;
    }

    /**
     * 设置此合约当天的交易数量
     *
     * @param txCount 此合约当天的交易数量
     */
    public void setTxCount(Integer txCount) {
        this.txCount = txCount;
    }

    /**
     * 获取此合约当天的tst流通量
     *
     * @return tst_sum - 此合约当天的tst流通量
     */
    public BigDecimal getTstSum() {
        return tstSum;
    }

    /**
     * 设置此合约当天的tst流通量
     *
     * @param tstSum 此合约当天的tst流通量
     */
    public void setTstSum(BigDecimal tstSum) {
        this.tstSum = tstSum;
    }

    /**
     * 获取此合约当天的tsg流通量
     *
     * @return tsg_sum - 此合约当天的tsg流通量
     */
    public BigDecimal getTsgSum() {
        return tsgSum;
    }

    /**
     * 设置此合约当天的tsg流通量
     *
     * @param tsgSum 此合约当天的tsg流通量
     */
    public void setTsgSum(BigDecimal tsgSum) {
        this.tsgSum = tsgSum;
    }

    /**
     * 获取此合约当天的活跃地址数
     *
     * @return active_address_count - 此合约当天的活跃地址数
     */
    public Integer getActiveAddressCount() {
        return activeAddressCount;
    }

    /**
     * 设置此合约当天的活跃地址数
     *
     * @param activeAddressCount 此合约当天的活跃地址数
     */
    public void setActiveAddressCount(Integer activeAddressCount) {
        this.activeAddressCount = activeAddressCount;
    }

    /**
     * 获取此合约当天的新地址数
     *
     * @return new_address_count - 此合约当天的新地址数
     */
    public Integer getNewAddressCount() {
        return newAddressCount;
    }

    /**
     * 设置此合约当天的新地址数
     *
     * @param newAddressCount 此合约当天的新地址数
     */
    public void setNewAddressCount(Integer newAddressCount) {
        this.newAddressCount = newAddressCount;
    }

    /**
     * 获取合约所属dapp名称
     *
     * @return dapp_name - 合约所属dapp名称
     */
    public String getDappName() {
        return dappName;
    }

    /**
     * 设置合约所属dapp名称
     *
     * @param dappName 合约所属dapp名称
     */
    public void setDappName(String dappName) {
        this.dappName = dappName == null ? null : dappName.trim();
    }
}