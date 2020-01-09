package com.github.TesraSupernet.service;

import com.github.TesraSupernet.model.common.ResponseBean;

public interface ISummaryService {

    ResponseBean getBlockChainLatestInfo();

    ResponseBean getBlockChainTps();

    ResponseBean getBlockChainDailySummary(Long startTime, Long endTime);

    ResponseBean getContractDailySummary(String contractHash, Long startTime, Long endTime);
}
