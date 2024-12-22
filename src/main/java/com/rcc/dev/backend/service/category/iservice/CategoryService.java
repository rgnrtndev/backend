package com.rcc.dev.backend.service.category.iservice;

import com.rcc.dev.backend.dto.category.CategoryRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;

import java.util.List;

public interface CategoryService {
    RCCResponse<Object> save(CategoryRequest categoryRequest);
    RCCResponse<Object> findAll();
}
