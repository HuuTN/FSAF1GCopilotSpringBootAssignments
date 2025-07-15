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

    // JPQL query hỗ trợ tìm kiếm theo keyword (name/description), categoryId, minPrice, maxPrice, có phân trang
    @Query("SELECT p FROM Product p WHERE (LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND (:categoryId IS NULL OR p.category.id = :categoryId) AND p.price BETWEEN :minPrice AND :maxPrice")
    Page<Product> searchProducts(@Param("keyword") String keyword,
                                 @Param("categoryId") Long categoryId,
                                 @Param("minPrice") double minPrice,
                                 @Param("maxPrice") double maxPrice,
                                 Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM product WHERE category_id = :categoryId", nativeQuery = true)
    long countProductsByCategoryId(@Param("categoryId") Long categoryId);
}
