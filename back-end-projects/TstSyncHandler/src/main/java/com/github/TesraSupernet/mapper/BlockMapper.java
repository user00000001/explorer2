package com.github.TesraSupernet.mapper;

import com.github.TesraSupernet.model.dao.Block;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface BlockMapper extends Mapper<Block> {

    void batchInsert(List<Block> list);

}