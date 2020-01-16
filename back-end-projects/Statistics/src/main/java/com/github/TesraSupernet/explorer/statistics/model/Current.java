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

import javax.persistence.*;

@Table(name = "tbl_current")
public class Current {
    /**
     * 当前同步的最新区块高度
     */
    @Column(name = "block_height")
    private Integer blockHeight;

    /**
     * 当前同步的最新交易数量
     */
    @Column(name = "tx_count")
    private Integer txCount;

    /**
     * 当前同步的最新TST ID数量
     */
    @Column(name = "tstid_count")
    private Integer tstidCount;

    /**
     * 当前同步的最新非TST ID相关的交易数量
     */
    @Column(name = "nontstid_tx_count")
    private Integer nontstidTxCount;

    /**
     * 获取当前同步的最新区块高度
     *
     * @return block_height - 当前同步的最新区块高度
     */
    public Integer getBlockHeight() {
        return blockHeight;
    }

    /**
     * 设置当前同步的最新区块高度
     *
     * @param blockHeight 当前同步的最新区块高度
     */
    public void setBlockHeight(Integer blockHeight) {
        this.blockHeight = blockHeight;
    }

    /**
     * 获取当前同步的最新交易数量
     *
     * @return tx_count - 当前同步的最新交易数量
     */
    public Integer getTxCount() {
        return txCount;
    }

    /**
     * 设置当前同步的最新交易数量
     *
     * @param txCount 当前同步的最新交易数量
     */
    public void setTxCount(Integer txCount) {
        this.txCount = txCount;
    }

    /**
     * 获取当前同步的最新TST ID数量
     *
     * @return tstid_count - 当前同步的最新TST ID数量
     */
    public Integer getTstidCount() {
        return tstidCount;
    }

    /**
     * 设置当前同步的最新TST ID数量
     *
     * @param tstidCount 当前同步的最新TST ID数量
     */
    public void setTstidCount(Integer tstidCount) {
        this.tstidCount = tstidCount;
    }

    /**
     * 获取当前同步的最新非TST ID相关的交易数量
     *
     * @return nontstid_tx_count - 当前同步的最新非TST ID相关的交易数量
     */
    public Integer getNontstidTxCount() {
        return nontstidTxCount;
    }

    /**
     * 设置当前同步的最新非TST ID相关的交易数量
     *
     * @param nontstidTxCount 当前同步的最新非TST ID相关的交易数量
     */
    public void setNontstidTxCount(Integer nontstidTxCount) {
        this.nontstidTxCount = nontstidTxCount;
    }
}