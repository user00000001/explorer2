package com.github.TesraSupernet.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.TesraSupernet.model.dao.Contract;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "tbl_contract")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractDto extends Contract {

    @Builder
    public ContractDto(String contractHash, String name, Integer createTime, Integer updateTime, Integer auditFlag, String contactInfo, String description, String type, String logo, String creator, Integer addressCount, Integer txCount, BigDecimal tstSum, BigDecimal tsgSum, String tokenSum, String category, String dappName, Integer dappstoreFlag, BigDecimal totalReward, BigDecimal lastweekReward, String abi, String code, String sourceCode, String channel) {
        super(contractHash, name, createTime, updateTime, auditFlag, contactInfo, description, type, logo, creator, addressCount, txCount, tstSum, tsgSum, tokenSum, category, dappName, dappstoreFlag, totalReward, lastweekReward, abi, code, sourceCode, channel);
    }
}
