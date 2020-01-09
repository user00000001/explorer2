package com.github.TesraSupernet.mapper;

import com.github.TesraSupernet.model.dto.TstidTxDetailDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface TstidTxDetailMapper extends Mapper<TstidTxDetailDto> {

    List<TstidTxDetailDto> selectTstidTxsByPage(@Param("tstid") String tstid, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    Integer selectTxCountByTstid(@Param("tstid") String tstId);

    TstidTxDetailDto selectOneByTxHash(@Param("txHash") String txHash);

}