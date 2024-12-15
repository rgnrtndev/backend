package com.rcc.dev.backend.controller;

import com.rcc.dev.backend.dto.category.CategoryRequest;
import com.rcc.dev.backend.model.Category;
import com.rcc.dev.backend.service.category.iservice.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/save")
    public Category save(@RequestBody CategoryRequest categoryRequest){
        return categoryService.save(categoryRequest);
    }
}
