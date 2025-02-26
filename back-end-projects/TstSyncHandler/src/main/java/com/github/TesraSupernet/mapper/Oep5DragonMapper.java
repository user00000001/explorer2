package com.github.TesraSupernet.mapper;

import com.github.TesraSupernet.model.dao.Oep5Dragon;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface Oep5DragonMapper extends Mapper<Oep5Dragon> {

    void batchInsert(List<Oep5Dragon> list);

}