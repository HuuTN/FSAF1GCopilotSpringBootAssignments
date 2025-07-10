package com.example.demojpa.service;

import com.example.demojpa.dto.ProductDTO;
import com.example.demojpa.entity.Product;
import com.example.demojpa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(this::toDTO);
    }

    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id).map(this::toDTO);
    }

    public ProductDTO createProduct(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        // set category if needed
        return toDTO(productRepository.save(product));
    }

    public Optional<ProductDTO> updateProduct(Long id, ProductDTO dto) {
        return productRepository.findById(id).map(product -> {
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());
            // set category if needed
            return toDTO(productRepository.save(product));
        });
    }

    public void deleteProduct(Long id) {
        if (!productRepository.findById(id).isPresent()) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }

    private ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        // set categoryId if needed
        return dto;
    }
}
