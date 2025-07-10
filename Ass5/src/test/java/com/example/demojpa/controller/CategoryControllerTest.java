package com.example.demojpa.controller;

import com.example.demojpa.dto.CategoryDTO;
import com.example.demojpa.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.PageImpl;
import java.util.Optional;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CategoryService categoryService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetCategoryById_found() throws Exception {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(1L);
        dto.setName("cat1");
        when(categoryService.getCategoryById(1L)).thenReturn(Optional.of(dto));
        mockMvc.perform(get("/api/v1/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("cat1"));
    }

    @Test
    void testGetCategoryById_notFound() throws Exception {
        when(categoryService.getCategoryById(2L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/v1/categories/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateCategory() throws Exception {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(1L);
        dto.setName("cat1");
        when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(dto);
        mockMvc.perform(post("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("cat1"));
    }

    @Test
    void testGetAllCategories() throws Exception {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(1L);
        dto.setName("cat1");
        PageImpl<CategoryDTO> page = new PageImpl<>(Collections.singletonList(dto));
        when(categoryService.getAllCategories(any())).thenReturn(page);
        mockMvc.perform(get("/api/v1/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("cat1"));
    }

    @Test
    void testDeleteCategory() throws Exception {
        mockMvc.perform(delete("/api/v1/categories/1"))
                .andExpect(status().isNoContent());
    }
}
