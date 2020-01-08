package com.github.TesraSupernet.mapper;

import com.github.TesraSupernet.model.dao.TstidTxDetail;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface TstidTxDetailMapper extends Mapper<TstidTxDetail> {

    void batchInsert(List<TstidTxDetail> list);

}