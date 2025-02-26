package com.github.TesraSupernet.mapper;

import com.github.TesraSupernet.model.dto.DailySummaryDto;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface DailySummaryMapper extends Mapper<DailySummaryDto> {

    List<DailySummaryDto> selectSummaryByTime(@Param("startTime") Long startTime, @Param("endTime") Long endTime);

    Map<String,BigDecimal> selectAddrAndTstIdTotal(@Param("startTime") Long startTime);


}