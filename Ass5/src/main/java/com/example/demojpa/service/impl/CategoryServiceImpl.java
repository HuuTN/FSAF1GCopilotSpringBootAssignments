package com.example.demojpa.service.impl;

import com.example.demojpa.dto.CategoryDTO;
import com.example.demojpa.entity.Category;
import com.example.demojpa.repository.CategoryRepository;
import com.example.demojpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<CategoryDTO> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(this::toDTO);
    }

    @Override
    public Optional<CategoryDTO> getCategoryById(Long id) {
        return categoryRepository.findById(id).map(this::toDTO);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return toDTO(categoryRepository.save(category));
    }

    @Override
    public Optional<CategoryDTO> updateCategory(Long id, CategoryDTO dto) {
        return categoryRepository.findById(id).map(category -> {
            category.setName(dto.getName());
            return toDTO(categoryRepository.save(category));
        });
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.findById(id).isPresent()) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.deleteById(id);
    }

    private CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
}
