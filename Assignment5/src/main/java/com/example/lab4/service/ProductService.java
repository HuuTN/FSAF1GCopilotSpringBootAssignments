package com.example.lab4.service;

import com.example.lab4.model.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {

    Page<ProductDTO> getAll(Pageable pageable);

    Optional<ProductDTO> getById(Long id);

    ProductDTO create(ProductDTO dto);

    Optional<ProductDTO> update(Long id, ProductDTO dto);

    boolean delete(Long id);
}