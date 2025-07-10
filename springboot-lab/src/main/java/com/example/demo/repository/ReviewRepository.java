package com.example.demo.repository;

import com.example.demo.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Find all reviews for a product
    List<Review> findByProductId(Long productId);

    // Find all reviews by a user
    List<Review> findByUserId(Long userId);

    // Custom query: find reviews with rating above a value
    @Query("SELECT r FROM Review r WHERE r.rating >= :minRating")
    List<Review> findByMinRating(@Param("minRating") int minRating);
}
