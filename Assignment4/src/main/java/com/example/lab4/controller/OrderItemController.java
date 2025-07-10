package com.example.lab4.controller;

import com.example.lab4.dto.OrderItemDTO;
import com.example.lab4.service.OrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order-items")
@Tag(name = "OrderItem", description = "Order Item management APIs")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    @Operation(summary = "Get all order items (paginated)")
    public ResponseEntity<Page<OrderItemDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(orderItemService.getAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order item by id")
    public ResponseEntity<OrderItemDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderItemService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create new order item")
    public ResponseEntity<OrderItemDTO> create(@Valid @RequestBody OrderItemDTO dto) {
        return new ResponseEntity<>(orderItemService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update order item by id")
    public ResponseEntity<OrderItemDTO> update(@PathVariable Long id, @Valid @RequestBody OrderItemDTO dto) {
        return ResponseEntity.ok(orderItemService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order item by id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
