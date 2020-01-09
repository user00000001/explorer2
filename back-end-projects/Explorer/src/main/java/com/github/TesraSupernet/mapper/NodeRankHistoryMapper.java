package com.github.TesraSupernet.mapper;

import com.github.TesraSupernet.model.dao.NodeRankHistory;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface NodeRankHistoryMapper extends Mapper<NodeRankHistory> {

    Long selectCurrentRoundBlockHeight();

    List<NodeRankHistory> selectRecentNodeRankHistory(Long firstRoundBlockHeight);

}