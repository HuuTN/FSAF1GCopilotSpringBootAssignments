package com.example.demojpa.service;

import com.example.demojpa.dto.CategoryDTO;
import com.example.demojpa.entity.Category;
import com.example.demojpa.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private com.example.demojpa.service.impl.CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCategoryById_found() {
        Category category = new Category();
        category.setId(1L);
        category.setName("cat1");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        Optional<CategoryDTO> result = categoryService.getCategoryById(1L);
        assertTrue(result.isPresent());
        assertEquals("cat1", result.get().getName());
    }

    @Test
    void testGetCategoryById_notFound() {
        when(categoryRepository.findById(2L)).thenReturn(Optional.empty());
        Optional<CategoryDTO> result = categoryService.getCategoryById(2L);
        assertFalse(result.isPresent());
    }

    @Test
    void testCreateCategory() {
        CategoryDTO dto = new CategoryDTO();
        dto.setName("cat2");
        Category category = new Category();
        category.setId(2L);
        category.setName("cat2");
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        CategoryDTO created = categoryService.createCategory(dto);
        assertEquals("cat2", created.getName());
        assertEquals(2L, created.getId());
    }

    @Test
    void testGetAllCategories() {
        Category category = new Category();
        category.setId(1L);
        category.setName("cat1");
        Page<Category> page = new PageImpl<>(Collections.singletonList(category));
        when(categoryRepository.findAll(any(PageRequest.class))).thenReturn(page);
        Page<CategoryDTO> result = categoryService.getAllCategories(PageRequest.of(0, 10));
        assertEquals(1, result.getTotalElements());
        assertEquals("cat1", result.getContent().get(0).getName());
    }

    @Test
    void testDeleteCategory() {
        // Chỉ kiểm tra xóa thành công với ID hợp lệ (mock findById trả về entity)
        Category category = new Category();
        category.setId(1L);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        doNothing().when(categoryRepository).deleteById(1L);
        assertDoesNotThrow(() -> categoryService.deleteCategory(1L));
        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteCategory_notFound() {
        // Test riêng trường hợp ném exception khi ID không tồn tại
        when(categoryRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> categoryService.deleteCategory(2L));
    }

    @Test
    void testUpdateCategory_found() {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(1L);
        dto.setName("updated");
        Category category = new Category();
        category.setId(1L);
        category.setName("updated");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        Optional<CategoryDTO> result = categoryService.updateCategory(1L, dto);
        assertTrue(result.isPresent());
        assertEquals("updated", result.get().getName());
    }

    @Test
    void testUpdateCategory_notFound() {
        CategoryDTO dto = new CategoryDTO();
        when(categoryRepository.findById(2L)).thenReturn(Optional.empty());
        Optional<CategoryDTO> result = categoryService.updateCategory(2L, dto);
        assertFalse(result.isPresent());
    }
}
