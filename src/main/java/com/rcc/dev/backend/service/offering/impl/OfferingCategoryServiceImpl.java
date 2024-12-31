package com.rcc.dev.backend.service.offering.impl;

import com.rcc.dev.backend.constant.ResponseCode;
import com.rcc.dev.backend.dto.offering.OfferingCategoryRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.OfferingCategory;
import com.rcc.dev.backend.repository.OfferingCategoryRepository;
import com.rcc.dev.backend.service.offering.iservice.OfferingService;
import com.rcc.dev.backend.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OfferingCategoryServiceImpl implements OfferingService {

    private final OfferingCategoryRepository offeringCategoryRepository;

    @Override
    public RCCResponse<Object> update(HttpServletRequest httpServletRequest, OfferingCategoryRequest offeringCategoryRequest) {
        try {
            OfferingCategory offeringCategory;
            if (Objects.isNull(offeringCategoryRequest.getId()) || offeringCategoryRequest.getId().equals(0L)) {
                offeringCategory = new OfferingCategory();
                offeringCategory.setOfferingName(offeringCategoryRequest.getOfferingName());
            } else {
                var offeringOpt = offeringCategoryRepository.findById(offeringCategoryRequest.getId());
                offeringCategory = offeringOpt.get();
                offeringCategory.setOfferingName(offeringCategoryRequest.getOfferingName());
            }
            var saveOfferingCategory = offeringCategoryRepository.save(offeringCategory);
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_SAVE_DATA,
                    ResponseCode.CommonEng.SUCCESS_SAVE_DATA,
                    saveOfferingCategory
            );
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
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
