package com.example.usermanagement.controller;

import com.example.usermanagement.dto.OrderPostRequest;
import com.example.usermanagement.dto.OrderPostResponse;
import com.example.usermanagement.entity.Order;
import com.example.usermanagement.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@Tag(name = "Order API", description = "API for Order management")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    @Operation(summary = "Create new order")
    public ResponseEntity<OrderPostResponse> createOrder(@RequestBody OrderPostRequest request) {
        Order order = orderService.createOrder(request.getUserId(), request.getProductId(), request.getQuantity());
        return new ResponseEntity<>(
                new OrderPostResponse(order.getId(), order.getStatus(), "Order created successfully!"),
                HttpStatus.CREATED
        );
    }
} 