package com.rcc.dev.backend.controller;

import com.rcc.dev.backend.dto.offering.OfferingChartDto;
import com.rcc.dev.backend.dto.offering.OfferingRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.service.offering.iservice.OfferingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/offering")
@RequiredArgsConstructor
public class OfferingController {
    private final OfferingService offeringService;

    @GetMapping("/list")
    public ResponseEntity<RCCResponse<Object>> listCategories(HttpServletRequest httpServletRequest){
        return offeringService.list(httpServletRequest);
    }

    @PostMapping("/update")
    public ResponseEntity<RCCResponse<Object>> update(HttpServletRequest httpServletRequest, @RequestBody OfferingRequest offeringRequest){
        return offeringService.update(httpServletRequest, offeringRequest);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<RCCResponse<Object>> detail(HttpServletRequest httpServletRequest, @PathVariable("id") Long id){
        return offeringService.detail(httpServletRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RCCResponse<Object>> delete(HttpServletRequest httpServletRequest, @PathVariable("id") Long id){
        return offeringService.delete(httpServletRequest, id);
    }

    @GetMapping("/chart")
    public ResponseEntity<RCCResponse<Object>> chart(HttpServletRequest httpServletRequest){
        return offeringService.offeringChart(httpServletRequest);
    }
}
