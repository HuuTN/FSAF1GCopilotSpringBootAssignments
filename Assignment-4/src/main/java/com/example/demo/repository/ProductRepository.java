package com.example.demo.repository;

import com.example.demo.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // a JPQL query to search for products by a keyword in name and a maximum price
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE CONCAT('%', LOWER(:keyword), '%') AND p.price <= :maxPrice")
    Page<Product> searchByNameAndPrice(@Param("keyword") String keyword, @Param("maxPrice") Double maxPrice, org.springframework.data.domain.Pageable pageable);

    // a native sql query to count the number of products for a given category ID
    @Query(value = "SELECT COUNT(*) FROM products WHERE category_id = :categoryId", nativeQuery = true)
    Long countByCategoryId(@Param("categoryId") Long categoryId);

}
