package com.github.TesraSupernet.mapper;

import com.github.TesraSupernet.model.dao.Oep8;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
@Component
public interface Oep8Mapper extends Mapper<Oep8> {

    List<Oep8> selectApprovedRecords();

}