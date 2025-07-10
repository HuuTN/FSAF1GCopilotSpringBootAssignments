package com.example.demojpa.controller;

import com.example.demojpa.dto.ProductDTO;
import com.example.demojpa.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetProductById_found() throws Exception {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setName("prod1");
        when(productService.getProductById(1L)).thenReturn(Optional.of(dto));
        mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("prod1"));
    }

    @Test
    void testGetProductById_notFound() throws Exception {
        when(productService.getProductById(2L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/v1/products/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateProduct() throws Exception {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setName("prod1");
        when(productService.createProduct(any(ProductDTO.class))).thenReturn(dto);
        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("prod1"));
    }

    @Test
    void testGetAllProducts() throws Exception {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setName("prod1");
        PageImpl<ProductDTO> page = new PageImpl<>(Collections.singletonList(dto));
        when(productService.getAllProducts(any())).thenReturn(page);
        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("prod1"));
    }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/v1/products/1"))
                .andExpect(status().isNoContent());
    }
}
