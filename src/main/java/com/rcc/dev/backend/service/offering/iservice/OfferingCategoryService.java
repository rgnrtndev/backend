package com.rcc.dev.backend.service.offering.iservice;

import com.rcc.dev.backend.dto.offering.OfferingCategoryRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface OfferingCategoryService {
    RCCResponse<Object> update(HttpServletRequest httpServletRequest, OfferingCategoryRequest offeringCategoryRequest);
    RCCResponse<Object> list(HttpServletRequest httpServletRequest);
    RCCResponse<Object> detail(HttpServletRequest httpServletRequest, Long id);
    RCCResponse<Object> delete(HttpServletRequest httpServletRequest, Long id);
}
