package com.github.TesraSupernet.explorer.statistics.mapper;

import com.github.TesraSupernet.explorer.statistics.model.NodeOverview;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface NodeOverviewMapper extends Mapper<NodeOverview> {

    void updateBlkCntToNxtRnd(Long blkCntToNxtRnd);

}