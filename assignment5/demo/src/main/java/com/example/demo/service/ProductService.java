package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;

public interface ProductService {
    Product createProduct(ProductDTO productDTO);
}
