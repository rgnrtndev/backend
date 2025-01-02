package com.rcc.dev.backend.service.offering.iservice;

import com.rcc.dev.backend.dto.offering.OfferingCategoryRequest;
import com.rcc.dev.backend.dto.offering.OfferingRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.OfferingCategory;
import jakarta.servlet.http.HttpServletRequest;

public interface OfferingService {
    RCCResponse<Object> update(HttpServletRequest httpServletRequest, OfferingRequest offeringRequest);
    RCCResponse<Object> list(HttpServletRequest httpServletRequest);
    RCCResponse<Object> detail(HttpServletRequest httpServletRequest, Long id);
    RCCResponse<Object> delete(HttpServletRequest httpServletRequest, Long id);
    RCCResponse<Object> offeringChart(HttpServletRequest httpServletRequest);
}
