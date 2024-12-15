package com.rcc.dev.backend.repository;

import com.rcc.dev.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
