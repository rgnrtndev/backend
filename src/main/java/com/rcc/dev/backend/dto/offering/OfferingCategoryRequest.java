package com.rcc.dev.backend.dto.offering;

import com.rcc.dev.backend.model.BaseDomain;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfferingCategoryRequest extends BaseDomain implements Serializable {
    @Serial
    private static final long serialVersionUID = 3639507794732558126L;
    private Long id;
    private String OfferingName;
}
