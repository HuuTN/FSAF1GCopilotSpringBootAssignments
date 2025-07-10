package com.example.usermanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.usermanagement.entity.Product;

public interface ProductService {
    Page<Product> searchProducts(String keyword, Double maxPrice, Pageable pageable);
}