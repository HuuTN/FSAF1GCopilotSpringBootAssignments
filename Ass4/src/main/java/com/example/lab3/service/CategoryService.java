package com.example.lab3.service;

import com.example.lab3.dto.CategoryDTO;
import com.example.lab3.entity.Category;
import com.example.lab3.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    private CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setParentId(category.getParent() != null ? category.getParent().getId() : null);
        return dto;
    }

    private Category toEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        // parent sẽ được set ở controller nếu cần
        return category;
    }

    public Page<CategoryDTO> getAll(Pageable pageable) {
        Page<Category> page = categoryRepository.findAll(pageable);
        return new PageImpl<>(
            page.getContent().stream().map(this::toDTO).collect(Collectors.toList()),
            pageable,
            page.getTotalElements()
        );
    }

    public Optional<CategoryDTO> getById(Long id) {
        return categoryRepository.findById(id).map(this::toDTO);
    }

    public CategoryDTO create(CategoryDTO dto) {
        Category category = toEntity(dto);
        if (dto.getParentId() != null) {
            category.setParent(categoryRepository.findById(dto.getParentId()).orElse(null));
        }
        Category saved = categoryRepository.save(category);
        return toDTO(saved);
    }

    public Optional<CategoryDTO> update(Long id, CategoryDTO dto) {
        if (!categoryRepository.existsById(id)) return Optional.empty();
        Category category = toEntity(dto);
        category.setId(id);
        if (dto.getParentId() != null) {
            category.setParent(categoryRepository.findById(dto.getParentId()).orElse(null));
        }
        Category saved = categoryRepository.save(category);
        return Optional.of(toDTO(saved));
    }

    public boolean delete(Long id) {
        if (!categoryRepository.existsById(id)) return false;
        categoryRepository.deleteById(id);
        return true;
    }
}
