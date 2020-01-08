package com.github.TesraSupernet.mapper;

import com.github.TesraSupernet.model.dao.Current;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
@Component
public interface CurrentMapper extends Mapper<Current> {

    void update(Current current);

    Integer selectBlockHeight();

}