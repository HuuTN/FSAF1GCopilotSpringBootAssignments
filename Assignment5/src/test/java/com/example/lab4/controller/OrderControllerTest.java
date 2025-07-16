package com.example.lab4.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.example.lab4.model.dto.OrderDTO;
import com.example.lab4.service.serviceimpl.OrderServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderServiceImpl orderService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAll() throws Exception {
        OrderDTO dto = new OrderDTO();
        dto.setId(1L);
        dto.setUserId(1L);
        dto.setOrderItemIds(java.util.List.of(100L));
        dto.setStatus("CREATED");
        when(orderService.getAll(PageRequest.of(0, 10))).thenReturn(new PageImpl<>(List.of(dto)));
    }

    @Test
    void testGetById_found() throws Exception {
        OrderDTO dto = new OrderDTO();
        dto.setId(1L);
        when(orderService.getById(1L)).thenReturn(Optional.of(dto));
        mockMvc.perform(get("/api/v1/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void testGetById_notFound() throws Exception {
        when(orderService.getById(2L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/v1/orders/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreate() throws Exception {
        OrderDTO dto = new OrderDTO();
        OrderDTO saved = new OrderDTO();
        saved.setId(10L);
        when(orderService.create(any(OrderDTO.class))).thenReturn(saved);
        mockMvc.perform(post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10L));
    }

    @Test
    void testUpdate_found() throws Exception {
        OrderDTO dto = new OrderDTO();
        OrderDTO updated = new OrderDTO();
        updated.setId(1L);
        when(orderService.update(eq(1L), any(OrderDTO.class))).thenReturn(Optional.of(updated));
        mockMvc.perform(put("/api/v1/orders/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void testUpdate_notFound() throws Exception {
        OrderDTO dto = new OrderDTO();
        when(orderService.update(eq(2L), any(OrderDTO.class))).thenReturn(Optional.empty());
        mockMvc.perform(put("/api/v1/orders/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDelete_found() throws Exception {
        when(orderService.delete(1L)).thenReturn(true);
        mockMvc.perform(delete("/api/v1/orders/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDelete_notFound() throws Exception {
        when(orderService.delete(2L)).thenReturn(false);
        mockMvc.perform(delete("/api/v1/orders/2"))
                .andExpect(status().isNotFound());
    }
}
