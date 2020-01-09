package com.github.TesraSupernet.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.TesraSupernet.model.dao.NodeInfoOnChain;

import javax.persistence.Table;

@Table(name = "tbl_node_info_on_chain")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NodeInfoOnChainDto extends NodeInfoOnChain {

    //@Builder
    public NodeInfoOnChainDto(Integer nodeRank, String name, Long currentStake, String progress, String detailUrl,
                              String publicKey, String address, Integer status, Long initPos, Long totalPos,
                              Long maxAuthorize, String nodeProportion, String currentStakePercentage) {
        super(nodeRank, name, currentStake, progress, detailUrl, publicKey, address, status, initPos, totalPos,
                maxAuthorize, nodeProportion, currentStakePercentage);
    }

}