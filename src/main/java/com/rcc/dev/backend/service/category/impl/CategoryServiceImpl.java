package com.rcc.dev.backend.service.category.impl;

import com.rcc.dev.backend.dto.category.CategoryRequest;
import com.rcc.dev.backend.model.Category;
import com.rcc.dev.backend.repository.CategoryRepository;
import com.rcc.dev.backend.service.category.iservice.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category save(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .id(categoryRequest.getId())
                .categoryName(categoryRequest.getCategoryName())
                .build();
        return category;
    }

    @Override
    public List<Category> findAll() {
        return List.of();
    }
}
