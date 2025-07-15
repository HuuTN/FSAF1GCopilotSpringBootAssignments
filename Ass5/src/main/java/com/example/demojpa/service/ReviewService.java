package com.example.demojpa.service;

import com.example.demojpa.dto.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface ReviewService {

    // Define methods for review-related operations here
    Page<ReviewDTO> getAllReviews(Pageable pageable);
    Optional<ReviewDTO> getReviewById(Long id);
    ReviewDTO createReview(ReviewDTO dto);
    Optional<ReviewDTO> updateReview(Long id, ReviewDTO dto);
    void deleteReview(Long id);
}