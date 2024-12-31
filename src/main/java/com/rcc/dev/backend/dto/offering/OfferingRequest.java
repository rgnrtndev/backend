package com.rcc.dev.backend.dto.offering;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rcc.dev.backend.model.BaseDomain;
import com.rcc.dev.backend.model.OfferingCategory;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferingRequest extends BaseDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 176630179421991431L;
    private Long id;
    private Date sabbathDate;
    private String imageBase64;
    private Long checkBy;
    private Long checkDate;
    private Long approveBy;
    private Date approveDate;
    private BigDecimal amount;
    private Long offeringCategoryId;
}
