package com.example.repository;

import com.example.entity.Product;
import com.example.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

// A Spring Data JPA repository for the Product entity.
public interface ProductRepository extends BaseRepository<Product, Long> {
    
    // A JPQL query to search for products by a keyword in the name and a maximum price.
    // Modify this query to support pagination and sorting.
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) AND p.price <= :maxPrice AND p.active = true")
    Page<Product> searchProducts(@Param("keyword") String keyword, @Param("maxPrice") Double maxPrice, Pageable pageable);
    
    // A native SQL query to count the number of products for a given category ID.
    @Query(value = "SELECT COUNT(*) FROM product WHERE category_id = :categoryId", nativeQuery = true)
    Long countProductsByCategoryId(@Param("categoryId") Long categoryId);
    
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId AND p.active = true")
    Page<Product> findByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);
    
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice AND p.active = true")
    Page<Product> findByPriceRange(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice, Pageable pageable);
    
    @Query("SELECT p FROM Product p WHERE p.stock < :threshold AND p.active = true")
    List<Product> findLowStockProducts(@Param("threshold") Integer threshold);
    
    Optional<Product> findByNameIgnoreCase(String name);
    
    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.reviews WHERE p.id = :id AND p.active = true")
    Optional<Product> findByIdWithReviews(@Param("id") Long id);
    
    @Query("SELECT AVG(r.rating) FROM Product p JOIN p.reviews r WHERE p.id = :productId")
    Double getAverageRating(@Param("productId") Long productId);
}
