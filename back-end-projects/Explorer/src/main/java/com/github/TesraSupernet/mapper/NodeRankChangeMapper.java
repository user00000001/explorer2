package com.github.TesraSupernet.mapper;

import com.github.TesraSupernet.model.dao.NodeRankChange;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface NodeRankChangeMapper extends Mapper<NodeRankChange> {

    List<NodeRankChange> selectAllChangeInfoInAsc();

    List<NodeRankChange> selectAllChangeInfoInDesc();
}