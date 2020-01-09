package com.github.TesraSupernet.model.dto;

import com.github.TesraSupernet.model.dao.DailySummary;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "tbl_daily_summary")
public class DailySummaryDto extends DailySummary {

    private Integer tstidTotal;

    private Integer addressTotal;

}
