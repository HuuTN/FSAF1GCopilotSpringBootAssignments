package com.example.demojpa.repository;

import com.example.demojpa.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Modify this query to support pagination and sorting.
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) AND p.price <= :maxPrice")
    Page<Product> searchProducts(@Param("keyword") String keyword, @Param("maxPrice") double maxPrice, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM product WHERE category_id = :categoryId", nativeQuery = true)
    long countProductsByCategoryId(@Param("categoryId") Long categoryId);
}
