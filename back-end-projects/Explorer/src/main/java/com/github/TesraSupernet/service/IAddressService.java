package com.github.TesraSupernet.service;

import com.github.TesraSupernet.model.common.ResponseBean;

public interface IAddressService {

    ResponseBean queryAddressBalance(String address, String tokenType);

    ResponseBean queryAddressBalanceByAssetName(String address, String assetName);

    ResponseBean queryAddressBalanceByAssetName4Onto(String address, String assetName);

    ResponseBean queryTransferTxsByPage(String address, String assetName, Integer pageNumber, Integer pageSize);

    ResponseBean queryTransferTxsByTime(String address, String assetName, Long beginTime, Long endTime);

    ResponseBean queryTransferTxsByTime4Onto(String address, String assetName, Long beginTime, Long endTime, String addressType);

    ResponseBean queryTransferTxsByTimeAndPage4Onto(String address, String assetName, Long endTime, Integer pageSize, String addressType);

}
