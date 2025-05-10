package com.rcc.dev.backend.service.offering.impl;

import com.rcc.dev.backend.constant.ResponseCode;
import com.rcc.dev.backend.dto.offering.OfferingCategoryRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.OfferingCategory;
import com.rcc.dev.backend.repository.OfferingCategoryRepository;
import com.rcc.dev.backend.service.offering.iservice.OfferingCategoryService;
import com.rcc.dev.backend.service.offering.iservice.OfferingService;
import com.rcc.dev.backend.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OfferingCategoryServiceImpl implements OfferingCategoryService {

    private final OfferingCategoryRepository offeringCategoryRepository;

    @Override
    public ResponseEntity<RCCResponse<Object>> update(HttpServletRequest httpServletRequest, OfferingCategoryRequest offeringCategoryRequest) {
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
            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_SAVE_DATA,
                    ResponseCode.CommonEng.SUCCESS_SAVE_DATA,
                    saveOfferingCategory
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            ));
        }
    }

    @Override
    public ResponseEntity<RCCResponse<Object>> list(HttpServletRequest httpServletRequest) {
        try {
            var offeringCategories = offeringCategoryRepository.findAll();
            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_ALL_DATA,
                    ResponseCode.CommonEng.SUCCESS_GET_ALL_DATA,
                    offeringCategories
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            ));
        }
    }

    @Override
    public ResponseEntity<RCCResponse<Object>> detail(HttpServletRequest httpServletRequest, Long id) {
        try {
            var offeringDetail = offeringCategoryRepository.findById(id);
            if(offeringDetail.isEmpty()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ResponseUtil.response(
                        ResponseCode.SUCCESS_RESPONSE_CODE,
                        ResponseCode.CommonIdn.DATA_NOT_FOUND,
                        ResponseCode.CommonEng.DATA_NOT_FOUND,
                        offeringDetail
                ));
            }
            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_DATA_DETAIL,
                    ResponseCode.CommonEng.SUCCESS_GET_DATA_DETAIL,
                    offeringDetail
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            ));
        }
    }

    @Override
    public ResponseEntity<RCCResponse<Object>> delete(HttpServletRequest httpServletRequest, Long id) {
        try{
            var offeringDetail = offeringCategoryRepository.findById(id);
            if(offeringDetail.isEmpty()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ResponseUtil.response(
                        ResponseCode.SUCCESS_RESPONSE_CODE,
                        ResponseCode.CommonIdn.DATA_NOT_FOUND,
                        ResponseCode.CommonEng.DATA_NOT_FOUND,
                        offeringDetail
                ));
            }
            offeringCategoryRepository.delete(offeringDetail.get());
            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_DELETED_DATA,
                    ResponseCode.CommonEng.SUCCESS_DELETED_DATA
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            ));
        }
    }
}
