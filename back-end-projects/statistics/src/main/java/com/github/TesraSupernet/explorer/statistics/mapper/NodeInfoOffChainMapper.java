package com.github.TesraSupernet.explorer.statistics.mapper;

import com.github.TesraSupernet.explorer.statistics.model.NodeInfoOffChain;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface NodeInfoOffChainMapper extends Mapper<NodeInfoOffChain> {
    String selectNameByPublicKey(String publicKey);
}