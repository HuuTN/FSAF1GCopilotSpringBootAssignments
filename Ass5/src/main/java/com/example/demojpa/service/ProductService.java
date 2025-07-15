package com.example.demojpa.service;

import com.example.demojpa.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public interface ProductService {

    Page<ProductDTO> getAllProducts(Pageable pageable);
    Optional<ProductDTO> getProductById(Long id);
    ProductDTO createProduct(ProductDTO dto);
    Optional<ProductDTO> updateProduct(Long id, ProductDTO dto);
    void deleteProduct(Long id);
}