package com.rcc.dev.backend.dto.offering.pojo;

import java.math.BigDecimal;
import java.util.Date;

public interface OfferingChartPojo {
    Long getId();
    Date getSabbathDate();
    String getOfferingName();
    BigDecimal getAmount();
}
