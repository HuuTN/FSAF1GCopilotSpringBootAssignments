package com.example.lab4.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lab4.model.entity.Product;

// A Spring Data JPA repository for the Product entity.
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // A JPQL query to search for products by a keyword in the name and a maximum price.
    // Modify this query to support pagination and sorting.
    @Query("SELECT p FROM Product p WHERE lower(p.name) LIKE lower(concat('%', :keyword, '%')) AND p.price <= :maxPrice")
    Page<Product> searchProducts(@Param("keyword") String keyword, @Param("maxPrice") double maxPrice, Pageable pageable);

    // A native SQL query to count the number of products for a given category ID.
    @Query(value = "SELECT count(*) FROM product WHERE category_id = :categoryId", nativeQuery = true)
    long countProductsByCategoryId(@Param("categoryId") Long categoryId);
}
