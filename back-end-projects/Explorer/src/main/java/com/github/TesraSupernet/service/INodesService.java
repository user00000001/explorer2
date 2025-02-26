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

package com.github.TesraSupernet.service;

import com.github.TesraSupernet.model.dao.*;
import com.github.TesraSupernet.model.dto.NodeInfoOnChainDto;

import java.util.List;

public interface INodesService {

    List<NodeInfoOnChainWithRankChange> getCurrentOnChainInfo();

    NodeInfoOffChain getCurrentOffChainInfo(String publicKey);

    List<NodeInfoOffChain> getCurrentOffChainInfo();

    NodeInfoOnChain getCurrentOnChainInfo(String publicKey);

    List<NodeBonus> getNodeBonusHistories();

    NodeBonus getLatestBonusByPublicKey(String publicKey);

    NodeBonus getLatestBonusByAddress(String address);

    List<NodeInfoOnChainWithBonus> getLatestBonusesWithInfos();

    List<NodeInfoOnChainWithBonus> searchNodeOnChainWithBonusByName(String name);

    List<NetNodeInfo> getActiveNetNodes();

    List<NetNodeInfo> getAllNodes();

    long getSyncNodeCount();

    long getCandidateNodeCount();

    long getConsensusNodeCount();

    long getSyncNodesCount();

    List<NodeInfoOffChain> getCurrentCandidateOffChainInfo();

    List<NodeInfoOffChain> getCurrentConsensusOffChainInfo();

    List<NodeRankChange> getNodeRankChange(boolean isDesc);

    List<NodeRankHistory> getRecentNodeRankHistory();
}
