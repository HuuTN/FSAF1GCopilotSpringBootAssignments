package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.Category;

public class CategoryDTO {
    private Long id;
    private String name;
    private List<CategoryDTO> children;

    public CategoryDTO(Long id, String name, List<CategoryDTO> children) {
        this.id = id;
        this.name = name;
        this.children = children;
    }

    public static CategoryDTO fromEntity(Category category) {
        List<CategoryDTO> childDTOs = null;
        if (category.getChildren() != null) {
            childDTOs = category.getChildren().stream()
                .map(CategoryDTO::fromEntity)
                .toList();
        }
        return new CategoryDTO(category.getId(), category.getName(), childDTOs);
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public List<CategoryDTO> getChildren() { return children; }
}

