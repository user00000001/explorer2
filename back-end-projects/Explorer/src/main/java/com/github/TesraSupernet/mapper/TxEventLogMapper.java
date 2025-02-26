package com.github.TesraSupernet.mapper;

import com.alibaba.fastjson.JSONArray;
import com.github.TesraSupernet.model.dto.TxBasicDto;
import com.github.TesraSupernet.model.dto.TxEventLogDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TxEventLogMapper extends Mapper<TxEventLogDto> {

    List<TxEventLogDto> selectTxsByPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    List<TxEventLogDto> selectNontstidTxsByPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    Integer queryTxCount(@Param("startTime") Long startTime, @Param("endTime") Long endTime);

    List<TxEventLogDto> selectTxsByCalledContractHash(@Param("calledContractHash") String contractHash, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    Integer selectCountByCalledContracthash(@Param("calledContractHash") String calledContractHash);

    List<TxBasicDto> selectTxsByBlockHeight(@Param("blockHeight") Integer blockHeight);


    BigDecimal queryTxFeeByParam(@Param("contractList") JSONArray contractList, @Param("startTime") Long startTime, @Param("endTime") Long endTime);

    Integer queryTxCountByParam(@Param("contractList") JSONArray contractList, @Param("startTime") Long startTime, @Param("endTime") Long endTime);


}