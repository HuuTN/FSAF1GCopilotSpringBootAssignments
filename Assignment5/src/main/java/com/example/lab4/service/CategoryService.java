package com.example.lab4.service;

import com.example.lab4.model.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {

    Page<CategoryDTO> getAll(Pageable pageable);

    Optional<CategoryDTO> getById(Long id);

    CategoryDTO create(CategoryDTO dto);

    Optional<CategoryDTO> update(Long id, CategoryDTO dto);

    boolean delete(Long id);
}