package com.github.TesraSupernet.model.dao;

import lombok.Builder;

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
     * 当前同步的最新TST ID相关的交易数量
     */
    @Column(name = "tstid_tx_count")
    private Integer tstidTxCount;

    @Builder
    public Current(Integer blockHeight, Integer txCount, Integer tstidCount, Integer tstidTxCount) {
        this.blockHeight = blockHeight;
        this.txCount = txCount;
        this.tstidCount = tstidCount;
        this.tstidTxCount = tstidTxCount;
    }

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
     * 获取当前同步的最新TST ID相关的交易数量
     *
     * @return tstid_tx_count - 当前同步的最新TST ID相关的交易数量
     */
    public Integer getTstidTxCount() {
        return tstidTxCount;
    }

    /**
     * 设置当前同步的最新TST ID相关的交易数量
     *
     * @param tstidTxCount 当前同步的最新TST ID相关的交易数量
     */
    public void setTstidTxCount(Integer tstidTxCount) {
        this.tstidTxCount = tstidTxCount;
    }
}