package com.example.usermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.usermanagement.entity.Product;
import com.example.usermanagement.repository.ProductRepository;
import com.example.usermanagement.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> searchProducts(String keyword, Double maxPrice, Pageable pageable) {
        return productRepository.searchProducts(keyword, maxPrice, pageable);
    }
}