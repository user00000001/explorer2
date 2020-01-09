package com.github.TesraSupernet.model.dto;

import com.github.TesraSupernet.model.dao.TstidTxDetail;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author zhouq
 * @version 1.0
 * @date 2019/4/28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "tbl_tstid_tx_detail")
public class TstidTxDetailDto extends TstidTxDetail {

    @Builder
    public TstidTxDetailDto(String txHash, Integer txType, String tstid, Integer txTime, Integer blockHeight, String description, BigDecimal fee) {
        super(txHash, txType, tstid, txTime, blockHeight, description, fee);
    }
}
