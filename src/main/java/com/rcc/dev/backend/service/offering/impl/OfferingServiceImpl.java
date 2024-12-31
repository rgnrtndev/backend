package com.rcc.dev.backend.service.offering.impl;

import com.rcc.dev.backend.dto.offering.OfferingCategoryRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.OfferingCategory;
import com.rcc.dev.backend.service.offering.iservice.OfferingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfferingServiceImpl implements OfferingService {

    private final OfferingService offeringService;

    @Override
    public RCCResponse<Object> update(HttpServletRequest httpServletRequest, OfferingCategoryRequest offeringCategoryRequest) {
        return null;
    }

    @Override
    public RCCResponse<Object> list(HttpServletRequest httpServletRequest) {
        return null;
    }

    @Override
    public RCCResponse<Object> detail(HttpServletRequest httpServletRequest, Long id) {
        return null;
    }

    @Override
    public RCCResponse<Object> delete(HttpServletRequest httpServletRequest, Long id) {
        return null;
    }
}
