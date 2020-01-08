package com.github.TesraSupernet.mapper;

import com.github.TesraSupernet.model.dao.Contract;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface ContractMapper extends Mapper<Contract> {

    void batchInsert(List<Contract> list);

}