package com.github.TesraSupernet.model.common;

import com.github.TesraSupernet.model.dao.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author user00000001
 * @version 0.0.1
 * @date 2020/1/2
 */
@Data
public class BatchBlockDto {
    //list会放到子线程进行操作，必须初始化成线程安全的list
    private List<Block> blocks = Collections.synchronizedList(new ArrayList<Block>());

    private List<Contract> contracts= Collections.synchronizedList(new ArrayList<Contract>());

    private List<TxDetail> txDetails= Collections.synchronizedList(new ArrayList<TxDetail>());

    private List<TxDetailDaily> txDetailDailys= Collections.synchronizedList(new ArrayList<TxDetailDaily>());

    private List<TxEventLog> txEventLogs= Collections.synchronizedList(new ArrayList<TxEventLog>());

    private List<TstidTxDetail> tstidTxDetails=Collections.synchronizedList(new ArrayList<TstidTxDetail>());

    private List<Oep4TxDetail> oep4TxDetails= Collections.synchronizedList(new ArrayList<Oep4TxDetail>());

    private List<Oep5TxDetail> oep5TxDetails= Collections.synchronizedList(new ArrayList<Oep5TxDetail>());

    private List<Oep5Dragon> oep5Dragons= Collections.synchronizedList(new ArrayList<Oep5Dragon>());

    private List<Oep8TxDetail> oep8TxDetails= Collections.synchronizedList(new ArrayList<Oep8TxDetail>());

}
