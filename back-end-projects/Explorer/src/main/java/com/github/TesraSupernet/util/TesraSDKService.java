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


package com.github.TesraSupernet.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.TesraSupernet.TstSdk;
import com.github.TesraSupernet.common.Address;
import com.github.TesraSupernet.common.Helper;
import com.github.TesraSupernet.config.ParamsConfig;
import com.github.TesraSupernet.core.transaction.Transaction;
import com.github.TesraSupernet.smartcontract.neovm.abi.BuildParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhouq
 * @date 2018/2/27
 */
@Slf4j
@Component
public class TesraSDKService {

    private static TesraSDKService instance = null;

    @Autowired
    private ParamsConfig paramsConfig;

    public static synchronized TesraSDKService getInstance(ParamsConfig param) {
        if (instance == null) {
            instance = new TesraSDKService(param);
        }
        return instance;
    }

    public TesraSDKService(ParamsConfig param) {
        this.paramsConfig = param;
    }

    public String getDDO(String tstId) {
        TstSdk tstSdk = getTstSdk();
        String ddoStr = "";
        try {
            ddoStr = tstSdk.nativevm().tstId().sendGetDDO(tstId);
        } catch (Exception e) {
            log.error("getDDO error...", e);
            e.printStackTrace();
        }

        return ddoStr;
    }


    /**
     * 获取账户余额，包括unboundtsg
     *
     * @param address
     * @return
     */
    public Map getNativeAssetBalance(String address) {
        Map<String, Object> balanceMap = new HashMap<>();
        try {
            TstSdk tstSdk = getTstSdk();
            balanceMap = (Map) tstSdk.getConnect().getBalance(address);
        } catch (Exception e) {
            log.error("getNativeAssetBalance error...", e);
            balanceMap.put("tsg", "0");
            balanceMap.put("tst", "0");
        }
        return balanceMap;
    }

    /**
     * 获取OEP4账户余额
     *
     * @param address
     * @return
     */
    public String getOep4AssetBalance(String address, String contractHash) {
        TstSdk tstSdk = getOep4TstSdk(contractHash);
        try {
            String balance = tstSdk.neovm().oep4().queryBalanceOf(address);
            return balance;
        } catch (Exception e) {
            log.error("getOep4AssetBalance error...", e);
            return "0";
        }
    }

    /**
     * 获取OEP5账户余额
     *
     * @param address
     * @return
     */
    public String getOep5AssetBalance(String address, String contractAddr) {
        TstSdk tstSdk = getOep5TstSdk(contractAddr);
        try {
            String balance = tstSdk.neovm().oep5().queryBalanceOf(address);
            return balance;
        } catch (Exception e) {
            log.error("getOep5AssetBalance error...", e);
            return "0";
        }
    }

    /**
     * 获取账户南瓜余额，包括unboundtsg
     *
     * @param address
     * @return
     */
    public JSONArray getOpe8AssetBalance(String address, String codeHash) {
        JSONArray balanceArray = new JSONArray();
        try {
            TstSdk tstSdk = getOep8TstSdk(codeHash);
            String balance = tstSdk.neovm().oep8().balancesOf(address);
            balanceArray = JSON.parseArray(balance);
        } catch (Exception e) {
            log.error("getOpe8AssetBalance error...", e);
        }
        return balanceArray;
    }

    /**
     * 获取unboundtsg
     *
     * @param address
     * @return
     */
    public String getUnBoundTsg(String address) {
        TstSdk tstSdk = getTstSdk();
        try {
            String unboundTsg = tstSdk.nativevm().tsg().unboundTsg(address);
            return new BigDecimal(unboundTsg).divide(ConstantParam.TST_TOTAL).toPlainString();
        } catch (Exception e) {
            log.error("getUnBoundTsg error...", e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据dapp合约hash获取绑定的节点信息
     * @param contractHash
     * @param dappContractHash
     * @return
     */
    public Map getDappBindedNodeInfo(String contractHash, String dappContractHash) {
        Map<String, Object> rsMap = new HashMap<>();
        rsMap.put("node_name", "");
        rsMap.put("node_pubkey", "");
        try {
            TstSdk tstSdk = getTstSdk();

            List paramList = new ArrayList();
            paramList.add("get_binded_node".getBytes());

            List args = new ArrayList();
            args.add(Helper.hexToBytes(dappContractHash));
            paramList.add(args);
            byte[] params = BuildParams.createCodeParamsScript(paramList);

            Transaction tx = tstSdk.vm().makeInvokeCodeTransaction(Helper.reverse(contractHash), null, params, null, 20000, 500);
            Object obj = tstSdk.getConnect().sendRawTransactionPreExec(tx.toHexString());

            String result = ((JSONObject) obj).getString("Result");
            log.info("contracthash:{},bindedNodeinfo:{}", dappContractHash, result);
            if (com.github.TesraSupernet.util.Helper.isNotEmptyAndNull(result)) {
                Map map = (Map) BuildParams.deserializeItem(Helper.hexToBytes(result));
                rsMap.put("node_name", new String(Helper.hexToBytes(((String) map.get("node_name")))));
                rsMap.put("node_pubkey", map.get("node_pubkey"));
            }
            return rsMap;
        } catch (Exception e) {
            log.error("dappContractHash:{} getDappBindedNodeInfo error...", dappContractHash,e);
        }
        return rsMap;
    }


    /**
     * 根据dapp合约hash获取绑定的onid和钱包账户
     * @param contractHash
     * @param dappContractHash
     * @return
     */
    public Map getDappBindedTstidAndAccount(String contractHash, String dappContractHash) {
        Map<String, Object> rsMap = new HashMap<>();
        rsMap.put("receive_account", "");
        rsMap.put("tstid", "");
        try {
            TstSdk tstSdk = getTstSdk();

            List paramList = new ArrayList();
            paramList.add("get_binded_dapp".getBytes());

            List args = new ArrayList();
            args.add(Helper.hexToBytes(dappContractHash));
            paramList.add(args);
            byte[] params = BuildParams.createCodeParamsScript(paramList);

            Transaction tx = tstSdk.vm().makeInvokeCodeTransaction(Helper.reverse(contractHash), null, params, null, 20000, 500);
            Object obj = tstSdk.getConnect().sendRawTransactionPreExec(tx.toHexString());

            String result = ((JSONObject) obj).getString("Result");
            log.info("contracthash:{},bindedAccountinfo:{}", dappContractHash, result);
            if (com.github.TesraSupernet.util.Helper.isNotEmptyAndNull(result)) {
                Map map = (Map) BuildParams.deserializeItem(Helper.hexToBytes(result));
                rsMap.put("receive_account", Address.parse((String) map.get("receive_account")).toBase58());
                rsMap.put("tstid", new String(Helper.hexToBytes((String) map.get("tstid"))));
            }
            return rsMap;
        } catch (Exception e) {
            log.error("getDappBindedTstidAndAccount error...", e);
        }
        return rsMap;
    }


    private TstSdk getTstSdk() {
        TstSdk wm = TstSdk.getInstance();
        wm.setRestful(paramsConfig.MASTERNODE_RESTFUL_URL);
        return wm;
    }

    private TstSdk getOep8TstSdk(String codeHash) {
        TstSdk wm = TstSdk.getInstance();
        wm.setRestful(paramsConfig.MASTERNODE_RESTFUL_URL);
        wm.neovm().oep8().setContractAddress(codeHash);
        return wm;
    }

    private TstSdk getOep4TstSdk(String codeHash) {
        TstSdk wm = TstSdk.getInstance();
        wm.setRestful(paramsConfig.MASTERNODE_RESTFUL_URL);
        wm.neovm().oep4().setContractAddress(codeHash);
        return wm;
    }

    private TstSdk getOep5TstSdk(String codeHash) {
        TstSdk wm = TstSdk.getInstance();
        wm.setRestful(paramsConfig.MASTERNODE_RESTFUL_URL);
        wm.neovm().oep5().setContractAddress(codeHash);
        return wm;
    }

}
