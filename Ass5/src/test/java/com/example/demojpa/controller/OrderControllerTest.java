package com.example.demojpa.controller;

import com.example.demojpa.dto.OrderDTO;
import com.example.demojpa.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderService orderService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /api/v1/orders returns paginated list of orders")
    void getAllOrders() throws Exception {
        Mockito.when(orderService.getAllOrders(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(new OrderDTO())));
        mockMvc.perform(get("/api/v1/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("content").exists());
    }

    @Test
    @DisplayName("GET /api/v1/orders/{id} returns order if found")
    void getOrderById_found() throws Exception {
        Mockito.when(orderService.getOrderById(1L)).thenReturn(Optional.of(new OrderDTO()));
        mockMvc.perform(get("/api/v1/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    @DisplayName("GET /api/v1/orders/{id} returns 404 if not found")
    void getOrderById_notFound() throws Exception {
        Mockito.when(orderService.getOrderById(99L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/v1/orders/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /api/v1/orders creates order")
    void createOrder() throws Exception {
        OrderDTO request = new OrderDTO();
        OrderDTO response = new OrderDTO();
        Mockito.when(orderService.createOrder(any(OrderDTO.class))).thenReturn(response);
        mockMvc.perform(post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    @DisplayName("PUT /api/v1/orders/{id} updates order if found")
    void updateOrder_found() throws Exception {
        OrderDTO request = new OrderDTO();
        OrderDTO response = new OrderDTO();
        Mockito.when(orderService.updateOrder(eq(1L), any(OrderDTO.class))).thenReturn(Optional.of(response));
        mockMvc.perform(put("/api/v1/orders/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    @DisplayName("PUT /api/v1/orders/{id} returns 404 if not found")
    void updateOrder_notFound() throws Exception {
        OrderDTO request = new OrderDTO();
        Mockito.when(orderService.updateOrder(eq(99L), any(OrderDTO.class))).thenReturn(Optional.empty());
        mockMvc.perform(put("/api/v1/orders/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /api/v1/orders/{id} deletes order")
    void deleteOrder() throws Exception {
        Mockito.doNothing().when(orderService).deleteOrder(1L);
        mockMvc.perform(delete("/api/v1/orders/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("POST /api/v1/orders/{id}/cancel cancels order")
    void cancelOrder() throws Exception {
        Mockito.doNothing().when(orderService).cancelOrder(1L);
        mockMvc.perform(post("/api/v1/orders/1/cancel"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /api/v1/orders/{id}/cancel returns 404 if not found")
    void cancelOrder_notFound() throws Exception {
        Mockito.doThrow(new RuntimeException("not found")).when(orderService).cancelOrder(99L);
        mockMvc.perform(post("/api/v1/orders/99/cancel"))
                .andExpect(status().isNotFound());
    }
}
