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

@Table(name = "tbl_tstid_tx_detail")
public class TstidTxDetail {
    /**
     * 交易hash
     */
    @Id
    @Column(name = "tx_hash")
    private String txHash;

    /**
     * 区块链交易类型，208：部署合约交易 209：调用合约交易
     */
    @Column(name = "tx_type")
    private Integer txType;

    /**
     * TST ID
     */
    private String tstid;

    /**
     * 交易时间戳
     */
    @Column(name = "tx_time")
    private Integer txTime;

    /**
     * 区块高度
     */
    @Column(name = "block_height")
    private Integer blockHeight;

    /**
     * TST ID交易描述
     */
    private String description;

    /**
     * 交易手续费
     */
    private BigDecimal fee;

    /**
     * 获取交易hash
     *
     * @return tx_hash - 交易hash
     */
    public String getTxHash() {
        return txHash;
    }

    /**
     * 设置交易hash
     *
     * @param txHash 交易hash
     */
    public void setTxHash(String txHash) {
        this.txHash = txHash == null ? null : txHash.trim();
    }

    /**
     * 获取区块链交易类型，208：部署合约交易 209：调用合约交易
     *
     * @return tx_type - 区块链交易类型，208：部署合约交易 209：调用合约交易
     */
    public Integer getTxType() {
        return txType;
    }

    /**
     * 设置区块链交易类型，208：部署合约交易 209：调用合约交易
     *
     * @param txType 区块链交易类型，208：部署合约交易 209：调用合约交易
     */
    public void setTxType(Integer txType) {
        this.txType = txType;
    }

    /**
     * 获取TST ID
     *
     * @return tstid - TST ID
     */
    public String getTstid() {
        return tstid;
    }

    /**
     * 设置TST ID
     *
     * @param tstid TST ID
     */
    public void setTstid(String tstid) {
        this.tstid = tstid == null ? null : tstid.trim();
    }

    /**
     * 获取交易时间戳
     *
     * @return tx_time - 交易时间戳
     */
    public Integer getTxTime() {
        return txTime;
    }

    /**
     * 设置交易时间戳
     *
     * @param txTime 交易时间戳
     */
    public void setTxTime(Integer txTime) {
        this.txTime = txTime;
    }

    /**
     * 获取区块高度
     *
     * @return block_height - 区块高度
     */
    public Integer getBlockHeight() {
        return blockHeight;
    }

    /**
     * 设置区块高度
     *
     * @param blockHeight 区块高度
     */
    public void setBlockHeight(Integer blockHeight) {
        this.blockHeight = blockHeight;
    }

    /**
     * 获取TST ID交易描述
     *
     * @return description - TST ID交易描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置TST ID交易描述
     *
     * @param description TST ID交易描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取交易手续费
     *
     * @return fee - 交易手续费
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * 设置交易手续费
     *
     * @param fee 交易手续费
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
}