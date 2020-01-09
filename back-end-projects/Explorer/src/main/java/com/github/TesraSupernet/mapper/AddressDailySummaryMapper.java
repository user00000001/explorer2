package com.github.TesraSupernet.mapper;

import com.github.TesraSupernet.model.dao.AddressDailySummary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AddressDailySummaryMapper extends Mapper<AddressDailySummary> {

    Integer selectAllAddressCount(@Param("contract_hash") String contractHash);

}