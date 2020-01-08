package com.github.TesraSupernet.mapper;

import com.github.TesraSupernet.model.dao.Oep8TxDetail;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface Oep8TxDetailMapper extends Mapper<Oep8TxDetail> {

    void batchInsert(List<Oep8TxDetail> list);

}