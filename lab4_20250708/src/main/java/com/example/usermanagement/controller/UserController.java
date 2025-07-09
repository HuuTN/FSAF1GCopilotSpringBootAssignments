package com.example.usermanagement.controller;

import com.example.usermanagement.dto.UserDTO;
import com.example.usermanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User API", description = "CRUD API for User management")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Get all users with paging")
    public ResponseEntity<Page<UserDTO>> getAllUsers(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    @Operation(summary = "Create new user")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @Autowired
    private ProductService productService;

    @GetMapping("/products/search")
    @Operation(summary = "Search products by keyword and max price")
    public ResponseEntity<Page<Product>> searchProducts(
            @RequestParam String keyword,
            @RequestParam(required = false) Double maxPrice,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(productService.searchProducts(keyword, maxPrice, pageable));
    }
}