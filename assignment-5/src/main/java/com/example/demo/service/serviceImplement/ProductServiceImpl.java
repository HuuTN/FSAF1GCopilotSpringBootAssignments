package com.example.demo.service.serviceImplement;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl  implements ProductService {

    @Override
    public void updateProductStock(Long productId, int quantity) {
        // Implementation for updating product stock
    }

    @Override
    public Product getProductById(Long productId) {
        // Implementation for retrieving a product by its ID
        return null; // Placeholder return statement
    }

    @Override
    public void addProduct(Product product) {
        // Implementation for adding a new product
    }

    @Override
    public void deleteProduct(Long productId) {
        // Implementation for deleting a product by its ID
    }


}
