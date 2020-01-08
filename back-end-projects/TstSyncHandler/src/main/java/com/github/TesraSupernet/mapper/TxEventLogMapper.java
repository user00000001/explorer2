package com.github.TesraSupernet.mapper;

import com.github.TesraSupernet.model.dao.TxEventLog;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface TxEventLogMapper extends Mapper<TxEventLog> {

    void batchInsert(List<TxEventLog> list);

}