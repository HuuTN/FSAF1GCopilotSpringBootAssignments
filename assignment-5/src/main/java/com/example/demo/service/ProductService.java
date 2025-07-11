package com.example.demo.service;

import com.example.demo.entity.Product;

public interface ProductService {

    void updateProductStock(Long productId, int quantity);
    Product getProductById(Long productId);
    void addProduct(Product product);    
    void deleteProduct(Long productId);
    
}
