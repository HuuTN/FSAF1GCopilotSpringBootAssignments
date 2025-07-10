package com.example.demojpa.controller;

import com.example.demojpa.dto.OrderDTO;
import com.example.demojpa.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Operation(summary = "Get all orders", description = "Get paginated list of all orders")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of orders returned successfully")
    })
    @GetMapping
    public Page<OrderDTO> getAllOrders(Pageable pageable) {
        return orderService.getAllOrders(pageable);
    }

    @Operation(summary = "Get order by ID", description = "Get an order by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Order found"),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create order", description = "Create a new order")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Order created successfully")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createOrder(@RequestBody OrderDTO dto) {
        return orderService.createOrder(dto);
    }

    @Operation(summary = "Update order", description = "Update an existing order by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Order updated successfully"),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO dto) {
        return orderService.updateOrder(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete order", description = "Delete an order by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Order deleted successfully")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Cancel order", description = "Cancel an order by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Order cancelled successfully"),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id) {
        try {
            orderService.cancelOrder(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
