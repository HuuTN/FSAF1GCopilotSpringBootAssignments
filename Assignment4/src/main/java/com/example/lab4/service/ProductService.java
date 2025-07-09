package com.example.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.lab4.dto.ProductDTO;
import com.example.lab4.entity.Category;
import com.example.lab4.entity.Product;
import com.example.lab4.repository.CategoryRepository;
import com.example.lab4.repository.ProductRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    private ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setCategoryId(product.getCategory() != null ? product.getCategory().getId() : null);
        return dto;
    }

    private Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId()).orElse(null);
            product.setCategory(category);
        }
        return product;
    }

    public Page<ProductDTO> getAll(Pageable pageable) {
        Page<Product> page = productRepository.findAll(pageable);
        return new PageImpl<>(
            page.getContent().stream().map(this::toDTO).collect(Collectors.toList()),
            pageable,
            page.getTotalElements()
        );
    }

    public Optional<ProductDTO> getById(Long id) {
        return productRepository.findById(id).map(this::toDTO);
    }

    public ProductDTO create(ProductDTO dto) {
        Product product = toEntity(dto);
        Product saved = productRepository.save(product);
        return toDTO(saved);
    }

    public Optional<ProductDTO> update(Long id, ProductDTO dto) {
        if (!productRepository.existsById(id)) return Optional.empty();
        Product product = toEntity(dto);
        product.setId(id);
        Product saved = productRepository.save(product);
        return Optional.of(toDTO(saved));
    }

    public boolean delete(Long id) {
        if (!productRepository.existsById(id)) return false;
        productRepository.deleteById(id);
        return true;
    }
}
