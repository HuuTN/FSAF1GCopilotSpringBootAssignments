package com.example.demojpa.service;

import com.example.demojpa.dto.CategoryDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface CategoryService {
    Page<CategoryDTO> getAllCategories(Pageable pageable);
    Optional<CategoryDTO> getCategoryById(Long id);
    CategoryDTO createCategory(CategoryDTO dto);
    Optional<CategoryDTO> updateCategory(Long id, CategoryDTO dto);
    void deleteCategory(Long id);

}
