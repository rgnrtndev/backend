package com.rcc.dev.backend.controller;

import com.rcc.dev.backend.dto.offering.OfferingCategoryRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.service.offering.iservice.OfferingCategoryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/offering/category")
@RequiredArgsConstructor
public class OfferingCategoryController {
    private final OfferingCategoryService offeringCategoryService;

    @GetMapping("/list")
    public RCCResponse<Object> list(HttpServletRequest httpServletRequest){
        return offeringCategoryService.list(httpServletRequest);
    }

    @PostMapping("/update")
    public RCCResponse<Object> update(HttpServletRequest httpServletRequest, OfferingCategoryRequest offeringCategoryRequest){
        return offeringCategoryService.update(httpServletRequest, offeringCategoryRequest);
    }

    @GetMapping("/detail/{id}")
    public RCCResponse<Object> detail(HttpServletRequest httpServletRequest, @PathVariable("id") Long id){
        return offeringCategoryService.detail(httpServletRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public RCCResponse<Object> delete(HttpServletRequest httpServletRequest, @PathVariable("id") Long id){
        return offeringCategoryService.delete(httpServletRequest, id);
    }
}
