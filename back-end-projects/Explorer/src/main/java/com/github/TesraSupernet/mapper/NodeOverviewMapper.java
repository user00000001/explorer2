package com.github.TesraSupernet.mapper;

import com.github.TesraSupernet.model.dao.NodeOverview;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface NodeOverviewMapper extends Mapper<NodeOverview> {

    Long selectBlkCountToNxtRnd();

}