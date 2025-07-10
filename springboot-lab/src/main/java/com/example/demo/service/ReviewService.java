package com.example.demo.service;

import com.example.demo.entity.Review;
import java.util.List;

public interface ReviewService {
    List<Review> findAll();
    List<Review> findByProductId(Long productId);
    List<Review> findByUserId(Long userId);
    List<Review> findByMinRating(int minRating);
}
