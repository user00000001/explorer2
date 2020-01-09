package com.github.TesraSupernet.mapper;

import com.github.TesraSupernet.model.dao.NodeInfoOffChain;
import com.github.TesraSupernet.model.dto.NodeInfoOffChainDto;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface NodeInfoOffChainMapper extends Mapper<NodeInfoOffChain> {

    NodeInfoOffChainDto selectByPublicKey(String publicKey);

    List<NodeInfoOffChain> selectAllConsensusNodeInfo();

    List<NodeInfoOffChain> selectAllCandidateNodeInfo();

}