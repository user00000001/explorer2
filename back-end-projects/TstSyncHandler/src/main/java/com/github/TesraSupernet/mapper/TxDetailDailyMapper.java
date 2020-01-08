package com.github.TesraSupernet.mapper;

import com.github.TesraSupernet.model.dao.TxDetailDaily;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface TxDetailDailyMapper extends Mapper<TxDetailDaily> {

    void batchInsert(List<TxDetailDaily> list);

}