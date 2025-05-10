package com.rcc.dev.backend.dto.offering;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferingChartDto {
    private Date sabbathDate;
    private String offeringCategoryName;
    private BigDecimal totalAmount;
}
