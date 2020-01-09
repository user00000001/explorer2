package com.github.TesraSupernet.model.dao;

import java.math.BigDecimal;
import javax.persistence.*;

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
     * 获取合约hash值
     *
     * @return contract_hash - 合约hash值
     */
    public String getContractHash() {
        return contractHash;
    }

    /**
     * 设置合约hash值
     *
     * @param contractHash 合约hash值
     */
    public void setContractHash(String contractHash) {
        this.contractHash = contractHash == null ? null : contractHash.trim();
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
    public BigDecimal getOngSum() {
        return tsgSum;
    }

    /**
     * 设置此合约当天的tsg流通量
     *
     * @param tsgSum 此合约当天的tsg流通量
     */
    public void setOngSum(BigDecimal tsgSum) {
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