package com.github.TesraSupernet.mapper;

import com.github.TesraSupernet.model.dao.Oep4TxDetail;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface Oep4TxDetailMapper extends Mapper<Oep4TxDetail> {

    void batchInsert(List<Oep4TxDetail> list);

}