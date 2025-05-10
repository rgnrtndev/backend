package com.rcc.dev.backend.service.offering.iservice;

import com.rcc.dev.backend.dto.offering.OfferingCategoryRequest;
import com.rcc.dev.backend.dto.offering.OfferingRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.OfferingCategory;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface OfferingService {
    ResponseEntity<RCCResponse<Object>> update(HttpServletRequest httpServletRequest, OfferingRequest offeringRequest);
    ResponseEntity<RCCResponse<Object>> list(HttpServletRequest httpServletRequest);
    ResponseEntity<RCCResponse<Object>> detail(HttpServletRequest httpServletRequest, Long id);
    ResponseEntity<RCCResponse<Object>> delete(HttpServletRequest httpServletRequest, Long id);
    ResponseEntity<RCCResponse<Object>> offeringChart(HttpServletRequest httpServletRequest);
    void sendOfferingChartUpdates();
    Map<String, Object> getOfferingChart();
}
