///*
// * Copyright (C) 2019-2020 The TesraSupernet Authors
// * This file is part of The TesraSupernet library.
// * The TesraSupernet is free software: you can redistribute it and/or modify
// * it under the terms of the GNU Lesser General Public License as published by
// * the Free Software Foundation, either version 3 of the License, or
// * (at your option) any later version.
// * The TesraSupernet is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU Lesser General Public License for more details.
// * You should have received a copy of the GNU Lesser General Public License
// * along with The TesraSupernet.  If not, see <http://www.gnu.org/licenses/>.
// */

package com.github.TesraSupernet.explorer.statistics.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.TesraSupernet.core.governance.GovernanceView;
import com.github.TesraSupernet.core.governance.PeerPoolItem;
import com.github.TesraSupernet.explorer.statistics.common.ParamsConfig;
import com.github.TesraSupernet.explorer.statistics.mapper.*;
import com.github.TesraSupernet.explorer.statistics.model.NodeInfoOnChain;
import com.github.TesraSupernet.explorer.statistics.model.NodeRankChange;
import com.github.TesraSupernet.explorer.statistics.model.NodeRankHistory;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Slf4j
@Service
@NoArgsConstructor
public class ConsensusNodeService {

    private ParamsConfig paramsConfig;

    private ObjectMapper objectMapper;

    private NodeOverviewMapper nodeOverviewMapper;

    private NodeRankChangeMapper nodeRankChangeMapper;

    private NodeInfoOnChainMapper nodeInfoOnChainMapper;

    private NodeRankHistoryMapper nodeRankHistoryMapper;

    private NodeInfoOffChainMapper nodeInfoOffChainMapper;

    private TstSdkService tstSdkService;

    @Autowired
    public ConsensusNodeService(ParamsConfig paramsConfig,
                                ObjectMapper objectMapper,
                                TstSdkService tstSdkService,
                                NodeOverviewMapper nodeOverviewMapper,
                                NodeRankChangeMapper nodeRankChangeMapper,
                                NodeInfoOnChainMapper nodeInfoOnChainMapper,
                                NodeRankHistoryMapper nodeRankHistoryMapper,
                                NodeInfoOffChainMapper nodeInfoOffChainMapper) {
        this.paramsConfig = paramsConfig;
        this.tstSdkService = tstSdkService;
        this.objectMapper = objectMapper;
        this.nodeOverviewMapper = nodeOverviewMapper;
        this.nodeRankChangeMapper = nodeRankChangeMapper;
        this.nodeInfoOnChainMapper = nodeInfoOnChainMapper;
        this.nodeRankHistoryMapper = nodeRankHistoryMapper;
        this.nodeInfoOffChainMapper = nodeInfoOffChainMapper;
    }

    public void updateBlockCountToNextRound() {
        long blockCntToNxtRound = getBlockCountToNextRound();
        if (blockCntToNxtRound < 0) {
            return;
        }
        try {
            nodeOverviewMapper.updateBlkCntToNxtRnd(blockCntToNxtRound);
            log.info("Updating block count to next round with value {}", blockCntToNxtRound);
        } catch (Exception e) {
            log.warn("Updating block count to next round with value {} failed: {}", blockCntToNxtRound, e.getMessage());
        }
    }

    public void updateNodeRankChange() {
        try {
            Long rankChangeBlockHeight = nodeRankChangeMapper.selectRankChangeBlockHeight();
            long currentBlockHeight = tstSdkService.getBlockHeight();
            if (rankChangeBlockHeight != null && rankChangeBlockHeight >= currentBlockHeight) {
                log.info("Current block height is {}, rank change block height is {}, no need to update", currentBlockHeight, rankChangeBlockHeight);
                return;
            }

            long lastRoundBlockHeight = nodeRankHistoryMapper.selectCurrentRoundBlockHeight();
            List<NodeInfoOnChain> currentNodeInfoOnChain = nodeInfoOnChainMapper.selectAll();
            if (currentNodeInfoOnChain == null) {
                log.warn("Selecting current node rank in height {} failed", currentBlockHeight);
                return;
            }
            int result = nodeRankChangeMapper.deleteAll();
            log.warn("Delete {} records in node rank history", result);
            for (NodeInfoOnChain currentRoundNode : currentNodeInfoOnChain) {
                NodeRankHistory lastRoundNodeRank = nodeRankHistoryMapper.selectNodeRankHistoryByPublicKeyAndBlockHeight(currentRoundNode.getPublicKey(), lastRoundBlockHeight);
                int rankChange = 0;
                if (lastRoundNodeRank != null) {
                    rankChange = lastRoundNodeRank.getNodeRank() - currentRoundNode.getNodeRank();
                }
                NodeRankChange nodeRankChange = NodeRankChange.builder()
                        .name(currentRoundNode.getName())
                        .address(currentRoundNode.getAddress())
                        .rankChange(rankChange)
                        .publicKey(currentRoundNode.getPublicKey())
                        .changeBlockHeight(currentBlockHeight)
                        .build();
                nodeRankChangeMapper.insert(nodeRankChange);
            }
        } catch (Exception e) {
            log.warn("Updating node rank change failed: {}", e.getMessage());
        }
    }

    public void updateNodeRankHistory() {
        long currentRoundBlockHeight;
        try {
            currentRoundBlockHeight = nodeRankHistoryMapper.selectCurrentRoundBlockHeight();
        } catch (NullPointerException e) {
            initNodeRankHistory();
            return;
        }
        try {
            long blockHeight = tstSdkService.getBlockHeight();
            long nextRoundBlockHeight = currentRoundBlockHeight + paramsConfig.getMaxStakingChangeCount();
            if (nextRoundBlockHeight > blockHeight) {
                log.info("Current block height is {}, next round block height should be {} ", blockHeight, nextRoundBlockHeight);
                return;
            }
            updateNodeRankHistoryFromNodeInfoOnChain(nextRoundBlockHeight);
        } catch (Exception e) {
            log.warn("Updating node position history failed {}", e.getMessage());
        }
    }

    private void initNodeRankHistory() {
        GovernanceView view = tstSdkService.getGovernanceView();
        if (view == null) {
            log.warn("Getting governance view in consensus node service failed:");
            return;
        }
        long currentRoundBlockHeight = view.height - paramsConfig.getMaxStakingChangeCount();
        updateNodeRankHistoryFromNodeInfoOnChain(currentRoundBlockHeight);
    }

    private void updateNodeRankHistoryFromNodeInfoOnChain(long currentRoundBlockHeight) {
        log.info("Updating node position history from node info on chain task begin");
        List<NodeInfoOnChain> nodeInfoOnChainList = nodeInfoOnChainMapper.selectAll();
        List<NodeRankHistory> nodePositionHistoryList = new ArrayList<>();
        for (NodeInfoOnChain node : nodeInfoOnChainList) {
            nodePositionHistoryList.add(new NodeRankHistory(node, currentRoundBlockHeight));
        }
        try {
            nodeRankHistoryMapper.batchInsertSelective(nodePositionHistoryList);
            log.info("Updating node position history from node info on chain task end");
        } catch (Exception e) {
            log.info("Updating node position history from node info on chain task failed: {}", e.getMessage());
        }
    }

    private long getBlockCountToNextRound() {
        GovernanceView view = tstSdkService.getGovernanceView();
        if (view == null) {
            log.warn("Getting governance view in consensus node service failed:");
            return -1;
        }
        long blockHeight = tstSdkService.getBlockHeight();
        return paramsConfig.getMaxStakingChangeCount() - (blockHeight - view.height);
    }

    public void updateConsensusNodeInfo() {
        Map peerPool = getPeerPool();
        List<NodeInfoOnChain> nodes = getNodesWithAttributes(peerPool);
        nodes.sort((v1, v2) -> Long.compare(v2.getInitPos() + v2.getTotalPos(), v1.getInitPos() + v1.getTotalPos()));
        List<NodeInfoOnChain> nodeInfos = calcNodeInfo(nodes);
        nodes = matchNodeName(nodeInfos);
        updateNodesTable(nodes);
    }

    private Map getPeerPool() {
        try {
            return tstSdkService.getSdk().nativevm().governance().getPeerPoolMap();
        } catch (Exception e) {
            log.error("Get peer pool map failed: {}", e.getMessage());
            tstSdkService.switchSyncNode();
            return getPeerPool();
        }
    }

    private List<NodeInfoOnChain> getNodesWithAttributes(Map peerPool) {
        List<NodeInfoOnChain> nodes = new ArrayList<>();
        for (Object obj : peerPool.values()) {
            PeerPoolItem item = (PeerPoolItem) obj;
            // candidate nodes and consensus nodes
            if (item.status != 1 && item.status != 2) {
                continue;
            }
            HashMap<String, Object> attribute = getAttributes(item.peerPubkey);
            NodeInfoOnChain node = new NodeInfoOnChain(item);
            node.setMaxAuthorize(Long.parseLong(attribute.get("maxAuthorize").toString()));
            node.setNodeProportion((100 - (int) attribute.get("t1PeerCost")) + "%");
            nodes.add(node);
        }
        return nodes;
    }

    private HashMap<String, Object> getAttributes(String pubKey) {
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
        };
        try {
            String result = tstSdkService.getSdk().nativevm().governance().getPeerAttributes(pubKey);
            return objectMapper.readValue(result, typeRef);
        } catch (Exception e) {
            log.error("Getting {}'s peer attributes failed: {}", pubKey, e.getMessage());
            tstSdkService.switchSyncNode();
            return getAttributes(pubKey);
        }
    }

    private List<NodeInfoOnChain> calcNodeInfo(List<NodeInfoOnChain> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            NodeInfoOnChain node = nodes.get(i);
            node.setNodeRank(i + 1);
            BigDecimal currentPos = new BigDecimal(node.getInitPos()).add(new BigDecimal(node.getTotalPos()));
            BigDecimal targetPos = new BigDecimal(node.getInitPos()).add(new BigDecimal(node.getMaxAuthorize()));
            node.setCurrentStake(currentPos.longValue());
            node.setProgress(currentPos.multiply(new BigDecimal(100)).divide(targetPos, 2, RoundingMode.HALF_UP) + "%");
            node.setDetailUrl(paramsConfig.getConsensusNodeDetailUrl() + node.getPublicKey());
            BigDecimal percent = new BigDecimal(node.getCurrentStake()).multiply(new BigDecimal(100)).divide(new BigDecimal(1000000000), 4, RoundingMode.HALF_UP);
            node.setCurrentStakePercentage(percent.toPlainString().concat("%"));
            nodes.set(i, node);
        }
        return nodes;
    }

    private void updateNodesTable(List<NodeInfoOnChain> nodes) {
        if (nodes.size() == 0) {
            log.warn("Updating NodeInfoOnchain table failed, nodes list is empty.");
            return;
        }
        int result = nodeInfoOnChainMapper.deleteAll();
        log.info("Updating NodeInfoOnchain table: delete {} nodes info.", result);
        try {
            result = nodeInfoOnChainMapper.batchInsert(nodes);
            log.info("Updating tbl_node_info_on_chain: insert {} nodes info.", result);
        } catch (Exception e) {
            log.error("Inserting {} into tbl_node_info_on_chain failed.", nodes.toString());
            log.error("Updating tbl_node_info_on_chain failed: {}", e.getMessage());
        }
    }

    private List<NodeInfoOnChain> matchNodeName(List<NodeInfoOnChain> nodeInfos) {
        int i = 0;
        for (NodeInfoOnChain info : nodeInfos) {
            try {
                String name = nodeInfoOffChainMapper.selectNameByPublicKey(info.getPublicKey());
                if (name == null) {
                    name = "";
                    log.warn("Selecting name by public key {} failed.", info.getPublicKey());
                }
                info.setName(name);
                nodeInfos.set(i, info);
            } catch (Exception e) {
                log.error("Matching node name failed: {}", info.getPublicKey());
                log.error(e.getMessage());
            }
            i++;
        }
        return nodeInfos;
    }
}
