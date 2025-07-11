package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult) {
        // Validate required fields
        if (bindingResult.hasErrors() ||
            product.getName() == null ||
            product.getPrice() == null ||
            product.getStock() == null ||
            product.getCategory() == null ||
            product.getCategory().getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        if (product.getPrice() < 0 || product.getStock() < 0) {
            return ResponseEntity.badRequest().build();
        }
        // Fetch category from DB to ensure it exists
        Category category = categoryRepository.findById(product.getCategory().getId()).orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }
        product.setCategory(category);
        Product saved = productRepository.save(product);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
