package com.rcc.dev.backend.service.category.iservice;

import com.rcc.dev.backend.dto.category.CategoryRequest;
import com.rcc.dev.backend.model.Category;

import java.util.List;

public interface CategoryService {
    Category save(CategoryRequest categoryRequest);
    List<Category> findAll();
}
