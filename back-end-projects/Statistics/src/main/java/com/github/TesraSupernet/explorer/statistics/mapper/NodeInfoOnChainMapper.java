package com.github.TesraSupernet.explorer.statistics.mapper;

import com.github.TesraSupernet.explorer.statistics.model.NodeInfoOnChain;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface NodeInfoOnChainMapper extends Mapper<NodeInfoOnChain> {
    // Self-defined SQL.
    int deleteAll();

    int batchInsert(List<NodeInfoOnChain> records);
}