package com.rcc.dev.backend.service.offering.impl;

import com.rcc.dev.backend.constant.ResponseCode;
import com.rcc.dev.backend.dto.offering.OfferingCategoryRequest;
import com.rcc.dev.backend.dto.offering.OfferingRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.Offering;
import com.rcc.dev.backend.model.OfferingCategory;
import com.rcc.dev.backend.repository.OfferingCategoryRepository;
import com.rcc.dev.backend.repository.OfferingRepository;
import com.rcc.dev.backend.service.offering.iservice.OfferingService;
import com.rcc.dev.backend.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OfferingServiceImpl implements OfferingService {

    private final OfferingRepository offeringRepository;
    private final OfferingCategoryRepository offeringCategoryRepository;

    @Override
    public RCCResponse<Object> update(HttpServletRequest httpServletRequest, OfferingRequest offeringRequest) {
        try {
            Offering offering = new Offering();
            if(Objects.isNull(offeringRequest.getId()) || offeringRequest.getId().equals(0L)){
                offering = Offering.builder()
                        .sabbathDate(offeringRequest.getSabbathDate())
                        .checkBy(offeringRequest.getCheckBy())
                        .checkDate(offeringRequest.getCheckDate())
                        .amount(offeringRequest.getAmount())
                        .approveBy(offeringRequest.getApproveBy())
                        .imageBase64(offeringRequest.getImageBase64()).build();
            }else {
                var offOpt = offeringRepository.findById(offeringRequest.getId());
                if(offOpt.isPresent()) {
                    offering = Offering.builder()
                            .sabbathDate(offeringRequest.getSabbathDate())
                            .checkBy(offeringRequest.getCheckBy())
                            .checkDate(offeringRequest.getCheckDate())
                            .amount(offeringRequest.getAmount())
                            .approveBy(offeringRequest.getApproveBy())
                            .imageBase64(offeringRequest.getImageBase64()).build();
                }
            }

            if (Objects.nonNull(offeringRequest.getOfferingCategoryId())) {
                var offeringCategory = offeringCategoryRepository.findById(offeringRequest.getOfferingCategoryId());
                if (offeringCategory.isPresent()) {
                    offering.setOfferingCategory(offeringCategory.get());
                }
            }

            var savedOffering = offeringRepository.save(offering);

            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_SAVE_DATA,
                    ResponseCode.CommonEng.SUCCESS_SAVE_DATA,
                    savedOffering
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
        try {
            var listOffering = offeringRepository.findAll();
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_ALL_DATA,
                    ResponseCode.CommonEng.SUCCESS_GET_ALL_DATA,
                    listOffering
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
    public RCCResponse<Object> detail(HttpServletRequest httpServletRequest, Long id) {
        try {
            var offeringDetail = offeringRepository.findById(id);
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_DATA_DETAIL,
                    ResponseCode.CommonEng.SUCCESS_GET_DATA_DETAIL,
                    offeringDetail
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
    public RCCResponse<Object> delete(HttpServletRequest httpServletRequest, Long id) {
        return null;
    }

    // TODO : fixing this chart query
    /**
     * total offering amounts in one month
     * total offering amounts by sabbath
     * total offering amounts by category
     * total offering reduce amounts to pay office boy or others
     * all with all reduce amounts
     * total offering amounts by one year with category
     * */
    @Override
    public RCCResponse<Object> offeringChart(HttpServletRequest httpServletRequest) {
        try {
            var offerings = offeringRepository.findOfferingChartData();
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_ALL_DATA,
                    ResponseCode.CommonEng.SUCCESS_GET_ALL_DATA,
                    offerings
            );
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }
}
