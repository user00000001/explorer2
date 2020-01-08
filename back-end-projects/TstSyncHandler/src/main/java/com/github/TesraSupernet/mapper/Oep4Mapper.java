package com.github.TesraSupernet.mapper;

import com.github.TesraSupernet.model.dao.Oep4;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
@Component
public interface Oep4Mapper extends Mapper<Oep4> {

    List<Oep4> selectApprovedRecords();

}