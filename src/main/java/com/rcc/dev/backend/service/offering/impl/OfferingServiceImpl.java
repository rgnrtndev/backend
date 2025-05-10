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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class OfferingServiceImpl implements OfferingService {

    private final OfferingRepository offeringRepository;
    private final OfferingCategoryRepository offeringCategoryRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public ResponseEntity<RCCResponse<Object>> update(HttpServletRequest httpServletRequest, OfferingRequest offeringRequest) {
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

            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_SAVE_DATA,
                    ResponseCode.CommonEng.SUCCESS_SAVE_DATA,
                    savedOffering
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
            var listOffering = offeringRepository.findAll();
            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_ALL_DATA,
                    ResponseCode.CommonEng.SUCCESS_GET_ALL_DATA,
                    listOffering
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
            var offeringDetail = offeringRepository.findById(id);
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
     * filter just only by offering category in one sabbath, one month, one year, or by triwulan
     * */
    @Override
    public ResponseEntity<RCCResponse<Object>> offeringChart(HttpServletRequest httpServletRequest) {
        try {
            var offerings = offeringRepository.getOfferingChartPojo();

            BigDecimal totalAmount = BigDecimal.ZERO;
            List<Map<String, Object>> offeringResponse = new ArrayList<>();

            for (var offering : offerings) {
                Map<String, Object> response = new HashMap<>();
                response.put("sabbath", offering.getSabbathDate());
                response.put("offeringName", offering.getOfferingName());
                response.put("id", offering.getId());
                response.put("amount", offering.getAmount());
                totalAmount = totalAmount.add(offering.getAmount());
                offeringResponse.add(response);
            }

            Map<String, Object> finalResponse = new HashMap<>();
            finalResponse.put("offerings", offeringResponse);
            finalResponse.put("totalAmount", totalAmount);

            // Broadcast to WebSocket clients
            messagingTemplate.convertAndSend("/topic/offering", finalResponse);

            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_ALL_DATA,
                    ResponseCode.CommonEng.SUCCESS_GET_ALL_DATA,
                    finalResponse
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
    public void sendOfferingChartUpdates() {
        var offerings = offeringRepository.getOfferingChartPojo();

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<Map<String, Object>> offeringResponse = new ArrayList<>();

        for (var offering : offerings) {
            Map<String, Object> response = new HashMap<>();
            response.put("sabbath", offering.getSabbathDate());
            response.put("offeringName", offering.getOfferingName());
            response.put("id", offering.getId());
            response.put("amount", offering.getAmount());
            totalAmount = totalAmount.add(offering.getAmount());
            offeringResponse.add(response);
        }

        Map<String, Object> finalResponse = new HashMap<>();
        finalResponse.put("offerings", offeringResponse);
        finalResponse.put("totalAmount", totalAmount);

        // Send the data to the topic "/user/offerings"
        messagingTemplate.convertAndSend("/user/offerings", finalResponse);
    }

    @Override
    public Map<String, Object> getOfferingChart() {
        var offerings = offeringRepository.getOfferingChartPojo();
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<Map<String, Object>> offeringResponse = new ArrayList<>();

        for (var offering : offerings) {
            Map<String, Object> response = new HashMap<>();
            response.put("sabbath", offering.getSabbathDate());
            response.put("offeringName", offering.getOfferingName());
            response.put("id", offering.getId());
            response.put("amount", offering.getAmount());
            totalAmount = totalAmount.add(offering.getAmount());
            offeringResponse.add(response);
        }

        Map<String, Object> finalResponse = new HashMap<>();
        finalResponse.put("offerings", offeringResponse);
        finalResponse.put("totalAmount", totalAmount);
        return finalResponse;
    }
}
