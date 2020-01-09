package com.github.TesraSupernet.mapper;

import com.github.TesraSupernet.model.dao.Config;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ConfigMapper extends Mapper<Config> {
}