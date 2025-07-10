package com.example.usermanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.usermanagement.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Custom query methods can be added here if needed
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% AND p.price <= :maxPrice")
    Page<Product> searchProducts(@Param("keyword") String keyword, @Param("maxPrice") Double maxPrice, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM products WHERE category_id = :categoryId", nativeQuery = true)
    int countProductsByCategoryId(@Param("categoryId") Long categoryId);
}
