package com.rcc.dev.backend.service.category.impl;

import com.rcc.dev.backend.constant.ResponseCode;
import com.rcc.dev.backend.constant.StatusResponse;
import com.rcc.dev.backend.dto.category.CategoryRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.Category;
import com.rcc.dev.backend.repository.CategoryRepository;
import com.rcc.dev.backend.service.category.iservice.CategoryService;
import com.rcc.dev.backend.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public RCCResponse<Object> save(CategoryRequest categoryRequest) {
        try {
            Category mapIntoCategory = Category.builder()
                    .id(categoryRequest.getId())
                    .categoryName(categoryRequest.getCategoryName())
                    .build();

            Category saveCategory = categoryRepository.save(mapIntoCategory);

            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_SAVE_DATA,
                    ResponseCode.CommonEng.SUCCESS_SAVE_DATA,
                    saveCategory
                    );
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }

    @Override
    public RCCResponse<Object> findAll() {
        try {
            List<Category> categories = categoryRepository.findAll();

            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_ALL_DATA,
                    ResponseCode.CommonEng.SUCCESS_GET_ALL_DATA,
                    categories
            );
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }
}
