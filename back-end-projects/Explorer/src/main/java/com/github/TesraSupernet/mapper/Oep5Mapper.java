package com.github.TesraSupernet.mapper;

import com.github.TesraSupernet.model.dao.Oep5;
import com.github.TesraSupernet.model.dto.Oep5DetailDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface Oep5Mapper extends Mapper<Oep5> {

    List<Oep5DetailDto> selectOep5Tokens();

    Oep5DetailDto selectOep5TokenDetail(@Param("contractHash") String contractHash);


}