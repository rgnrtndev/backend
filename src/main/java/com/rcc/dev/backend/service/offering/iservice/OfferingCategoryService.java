package com.rcc.dev.backend.service.offering.iservice;

import com.rcc.dev.backend.dto.offering.OfferingCategoryRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface OfferingCategoryService {
    ResponseEntity<RCCResponse<Object>> update(HttpServletRequest httpServletRequest, OfferingCategoryRequest offeringCategoryRequest);
    ResponseEntity<RCCResponse<Object>> list(HttpServletRequest httpServletRequest);
    ResponseEntity<RCCResponse<Object>> detail(HttpServletRequest httpServletRequest, Long id);
    ResponseEntity<RCCResponse<Object>> delete(HttpServletRequest httpServletRequest, Long id);
}
