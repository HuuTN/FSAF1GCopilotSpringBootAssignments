package com.example.lab4.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.lab4.dto.CategoryDTO;
import com.example.lab4.entity.Category;
import com.example.lab4.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {
    @InjectMocks
    private CategoryService categoryService;
    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCategoryServiceNotNull() {
        CategoryService categoryService = new CategoryService();
        assertNotNull(categoryService);
    }

    @Test
    void testGetAll() {
        Pageable pageable = mock(Pageable.class);
        List<Category> categories = List.of(new Category());
        when(categoryRepository.findAll(pageable)).thenReturn(new PageImpl<>(categories));
        Page<CategoryDTO> result = categoryService.getAll(pageable);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testGetById_found() {
        Category category = new Category();
        category.setId(1L);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        var result = categoryService.getById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testGetById_notFound() {
        when(categoryRepository.findById(2L)).thenReturn(Optional.empty());
        var result = categoryService.getById(2L);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreate() {
        CategoryDTO dto = new CategoryDTO();
        dto.setName("cat");
        dto.setParentId(1L);
        Category parent = new Category();
        parent.setId(1L);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(parent));
        Category saved = new Category();
        saved.setId(10L);
        saved.setName("cat");
        saved.setParent(parent);
        when(categoryRepository.save(any(Category.class))).thenReturn(saved);
        var result = categoryService.create(dto);
        assertNotNull(result);
        assertEquals(10L, result.getId());
    }

    @Test
    void testUpdate_found() {
        CategoryDTO dto = new CategoryDTO();
        dto.setName("cat");
        dto.setParentId(1L);
        when(categoryRepository.existsById(1L)).thenReturn(true);
        Category parent = new Category();
        parent.setId(1L);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(parent));
        Category saved = new Category();
        saved.setId(1L);
        saved.setName("cat");
        saved.setParent(parent);
        when(categoryRepository.save(any(Category.class))).thenReturn(saved);
        var result = categoryService.update(1L, dto);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testUpdate_notFound() {
        CategoryDTO dto = new CategoryDTO();
        when(categoryRepository.existsById(2L)).thenReturn(false);
        var result = categoryService.update(2L, dto);
        assertTrue(result.isEmpty());
    }

    @Test
    void testDelete_found() {
        when(categoryRepository.existsById(1L)).thenReturn(true);
        doNothing().when(categoryRepository).deleteById(1L);
        assertTrue(categoryService.delete(1L));
    }

    @Test
    void testDelete_notFound() {
        when(categoryRepository.existsById(2L)).thenReturn(false);
        assertFalse(categoryService.delete(2L));
    }
}
