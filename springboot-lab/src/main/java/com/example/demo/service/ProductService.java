package com.example.demo.service;

import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Page<Product> findAll(Pageable pageable);
    List<Product> searchByName(String keyword);
    List<Product> findExpensiveProducts(double minPrice);
    List<Product> findAll(Specification<Product> spec);
    Product save(Product product);
}
