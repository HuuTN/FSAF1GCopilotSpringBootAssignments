package com.example.demojpa.service;

import com.example.demojpa.dto.ProductDTO;
import com.example.demojpa.entity.Product;
import com.example.demojpa.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private com.example.demojpa.service.impl.ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProductById_found() {
        Product product = new Product();
        product.setId(1L);
        product.setName("test");
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Optional<ProductDTO> result = productService.getProductById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("test", result.get().getName());
    }

    @Test
    void testGetProductById_notFound() {
        when(productRepository.findById(2L)).thenReturn(Optional.empty());
        Optional<ProductDTO> result = productService.getProductById(2L);
        assertFalse(result.isPresent());
    }

    @Test
    void testCreateProduct() {
        ProductDTO dto = new ProductDTO();
        dto.setName("test");
        dto.setDescription("desc");
        dto.setPrice(100.0);
        Product product = new Product();
        product.setId(10L);
        product.setName("test");
        product.setDescription("desc");
        product.setPrice(100.0);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        ProductDTO created = productService.createProduct(dto);
        assertEquals("test", created.getName());
        assertEquals("desc", created.getDescription());
        assertEquals(100.0, created.getPrice());
        assertEquals(10L, created.getId());
    }

    @Test
    void testUpdateProduct_found() {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setName("updated");
        Product product = new Product();
        product.setId(1L);
        product.setName("updated");
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        Optional<ProductDTO> result = productService.updateProduct(1L, dto);
        assertTrue(result.isPresent());
        assertEquals("updated", result.get().getName());
    }

    @Test
    void testUpdateProduct_notFound() {
        ProductDTO dto = new ProductDTO();
        when(productRepository.findById(2L)).thenReturn(Optional.empty());
        Optional<ProductDTO> result = productService.updateProduct(2L, dto);
        assertFalse(result.isPresent());
    }

    @Test
    void testDeleteProduct_found() {
        Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).deleteById(1L);
        assertDoesNotThrow(() -> productService.deleteProduct(1L));
    }

    @Test
    void testDeleteProduct_notFound() {
        when(productRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> productService.deleteProduct(2L));
    }
}
