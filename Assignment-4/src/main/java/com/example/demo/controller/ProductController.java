package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAll() { return productRepository.findAll(); }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) { return productRepository.findById(id).orElse(null); }

    @PostMapping
    public Product create(@RequestBody Product product) { return productRepository.save(product); }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { productRepository.deleteById(id); }
}
