package com.github.TesraSupernet.explorer.statistics.mapper;

import com.github.TesraSupernet.explorer.statistics.model.NodeRankChange;
import com.github.TesraSupernet.explorer.statistics.model.NodeRankHistory;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface NodeRankChangeMapper extends Mapper<NodeRankChange> {

    Integer deleteAll();

    Long selectRankChangeBlockHeight();

}