package com.github.TesraSupernet.explorer.statistics.mapper;

import com.github.TesraSupernet.explorer.statistics.model.NetNodeInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface NetNodeInfoMapper extends Mapper<NetNodeInfo> {

    Integer updateWithLatestInfo(NetNodeInfo netNodeInfo);

}