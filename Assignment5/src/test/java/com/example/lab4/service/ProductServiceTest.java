package com.example.lab4.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.lab4.dto.ProductDTO;
import com.example.lab4.entity.Category;
import com.example.lab4.entity.Product;
import com.example.lab4.repository.CategoryRepository;
import com.example.lab4.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProductServiceNotNull() {
        ProductService productService = new ProductService();
        assertNotNull(productService);
    }

    @Test
    void testGetAll() {
        Pageable pageable = mock(Pageable.class);
        List<Product> products = List.of(new Product());
        when(productRepository.findAll(pageable)).thenReturn(new PageImpl<>(products));
        Page<ProductDTO> result = productService.getAll(pageable);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testGetById_found() {
        Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        var result = productService.getById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testGetById_notFound() {
        when(productRepository.findById(2L)).thenReturn(Optional.empty());
        var result = productService.getById(2L);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreate() {
        ProductDTO dto = new ProductDTO();
        dto.setName("n");
        dto.setPrice(10.0);
        dto.setStock(5);
        dto.setCategoryId(1L);
        Category category = new Category();
        category.setId(1L);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        Product saved = new Product();
        saved.setId(10L);
        saved.setName("n");
        saved.setPrice(10.0);
        saved.setStock(5);
        saved.setCategory(category);
        when(productRepository.save(any(Product.class))).thenReturn(saved);
        var result = productService.create(dto);
        assertNotNull(result);
        assertEquals(10L, result.getId());
    }

    @Test
    void testUpdate_found() {
        ProductDTO dto = new ProductDTO();
        dto.setName("n");
        dto.setPrice(10.0);
        dto.setStock(5);
        when(productRepository.existsById(1L)).thenReturn(true);
        Product saved = new Product();
        saved.setId(1L);
        saved.setName("n");
        saved.setPrice(10.0);
        saved.setStock(5);
        when(productRepository.save(any(Product.class))).thenReturn(saved);
        var result = productService.update(1L, dto);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testUpdate_notFound() {
        ProductDTO dto = new ProductDTO();
        when(productRepository.existsById(2L)).thenReturn(false);
        var result = productService.update(2L, dto);
        assertTrue(result.isEmpty());
    }

    @Test
    void testDelete_found() {
        when(productRepository.existsById(1L)).thenReturn(true);
        doNothing().when(productRepository).deleteById(1L);
        assertTrue(productService.delete(1L));
    }

    @Test
    void testDelete_notFound() {
        when(productRepository.existsById(2L)).thenReturn(false);
        assertFalse(productService.delete(2L));
    }
}
