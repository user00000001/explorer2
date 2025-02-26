package com.github.TesraSupernet.mapper;

import com.github.TesraSupernet.model.dao.TxDetail;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface TxDetailMapper extends Mapper<TxDetail> {

    void batchInsert(List<TxDetail> list);
}