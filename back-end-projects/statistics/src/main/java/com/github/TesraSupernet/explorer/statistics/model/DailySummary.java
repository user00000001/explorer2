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

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import javax.persistence.*;

@Data
@Builder
@Table(name = "tbl_daily_summary")
public class DailySummary {
    /**
     * 当天的UTC0点时间戳
     */
    @Id
    private Integer time;

    /**
     * 当天的区块数量
     */
    @Column(name = "block_count")
    private Integer blockCount;

    /**
     * 当天的交易数量
     */
    @Column(name = "tx_count")
    private Integer txCount;

    /**
     * 当天的活跃TST ID数量
     */
    @Column(name = "active_tstid_count")
    private Integer activeTstidCount;

    /**
     * 当天的新TST ID数量
     */
    @Column(name = "new_tstid_count")
    private Integer newTstidCount;

    /**
     * 当天的tst流通量
     */
    @Column(name = "tst_sum")
    private BigDecimal tstSum;

    /**
     * 当天的tsg流通量
     */
    @Column(name = "tsg_sum")
    private BigDecimal tsgSum;

    /**
     * 当天的活跃地址数量
     */
    @Column(name = "active_address_count")
    private Integer activeAddressCount;

    /**
     * 当天的新地址数量
     */
    @Column(name = "new_address_count")
    private Integer newAddressCount;

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
     * 获取当天的区块数量
     *
     * @return block_count - 当天的区块数量
     */
    public Integer getBlockCount() {
        return blockCount;
    }

    /**
     * 设置当天的区块数量
     *
     * @param blockCount 当天的区块数量
     */
    public void setBlockCount(Integer blockCount) {
        this.blockCount = blockCount;
    }

    /**
     * 获取当天的交易数量
     *
     * @return tx_count - 当天的交易数量
     */
    public Integer getTxCount() {
        return txCount;
    }

    /**
     * 设置当天的交易数量
     *
     * @param txCount 当天的交易数量
     */
    public void setTxCount(Integer txCount) {
        this.txCount = txCount;
    }

    /**
     * 获取当天的活跃TST ID数量
     *
     * @return active_tstid_count - 当天的活跃TST ID数量
     */
    public Integer getActiveTstidCount() {
        return activeTstidCount;
    }

    /**
     * 设置当天的活跃TST ID数量
     *
     * @param activeTstidCount 当天的活跃TST ID数量
     */
    public void setActiveTstidCount(Integer activeTstidCount) {
        this.activeTstidCount = activeTstidCount;
    }

    /**
     * 获取当天的新TST ID数量
     *
     * @return new_tstid_count - 当天的新TST ID数量
     */
    public Integer getNewTstidCount() {
        return newTstidCount;
    }

    /**
     * 设置当天的新TST ID数量
     *
     * @param newTstidCount 当天的新TST ID数量
     */
    public void setNewTstidCount(Integer newTstidCount) {
        this.newTstidCount = newTstidCount;
    }

    /**
     * 获取当天的tst流通量
     *
     * @return tst_sum - 当天的tst流通量
     */
    public BigDecimal getTstSum() {
        return tstSum;
    }

    /**
     * 设置当天的tst流通量
     *
     * @param tstSum 当天的tst流通量
     */
    public void setTstSum(BigDecimal tstSum) {
        this.tstSum = tstSum;
    }

    /**
     * 获取当天的tsg流通量
     *
     * @return tsg_sum - 当天的tsg流通量
     */
    public BigDecimal getTsgSum() {
        return tsgSum;
    }

    /**
     * 设置当天的tsg流通量
     *
     * @param tsgSum 当天的tsg流通量
     */
    public void setTsgSum(BigDecimal tsgSum) {
        this.tsgSum = tsgSum;
    }

    /**
     * 获取当天的活跃地址数量
     *
     * @return active_address_count - 当天的活跃地址数量
     */
    public Integer getActiveAddressCount() {
        return activeAddressCount;
    }

    /**
     * 设置当天的活跃地址数量
     *
     * @param activeAddressCount 当天的活跃地址数量
     */
    public void setActiveAddressCount(Integer activeAddressCount) {
        this.activeAddressCount = activeAddressCount;
    }

    /**
     * 获取当天的新地址数量
     *
     * @return new_address_count - 当天的新地址数量
     */
    public Integer getNewAddressCount() {
        return newAddressCount;
    }

    /**
     * 设置当天的新地址数量
     *
     * @param newAddressCount 当天的新地址数量
     */
    public void setNewAddressCount(Integer newAddressCount) {
        this.newAddressCount = newAddressCount;
    }
}