package com.example.demojpa.service.impl;

import com.example.demojpa.dto.ReviewDTO;
import com.example.demojpa.entity.Review;
import com.example.demojpa.entity.Product;
import com.example.demojpa.entity.User;
import com.example.demojpa.repository.ReviewRepository;
import com.example.demojpa.repository.ProductRepository;
import com.example.demojpa.repository.UserRepository;
import com.example.demojpa.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<ReviewDTO> getAllReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable).map(this::toDTO);
    }

    @Override
    public Optional<ReviewDTO> getReviewById(Long id) {
        return reviewRepository.findById(id).map(this::toDTO);
    }

    @Override
    public ReviewDTO createReview(ReviewDTO dto) {
        Review review = new Review();
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        if (dto.getProductId() != null) {
            productRepository.findById(dto.getProductId()).ifPresent(review::setProduct);
        }
        if (dto.getUserId() != null) {
            userRepository.findById(dto.getUserId()).ifPresent(review::setUser);
        }
        return toDTO(reviewRepository.save(review));
    }

    @Override
    public Optional<ReviewDTO> updateReview(Long id, ReviewDTO dto) {
        return reviewRepository.findById(id).map(review -> {
            review.setRating(dto.getRating());
            review.setComment(dto.getComment());
            if (dto.getProductId() != null) {
                productRepository.findById(dto.getProductId()).ifPresent(review::setProduct);
            }
            if (dto.getUserId() != null) {
                userRepository.findById(dto.getUserId()).ifPresent(review::setUser);
            }
            return toDTO(reviewRepository.save(review));
        });
    }

    @Override
    public void deleteReview(Long id) {
        if (!reviewRepository.findById(id).isPresent()) {
            throw new RuntimeException("Review not found");
        }
        reviewRepository.deleteById(id);
    }

    private ReviewDTO toDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        if (review.getProduct() != null) dto.setProductId(review.getProduct().getId());
        if (review.getUser() != null) dto.setUserId(review.getUser().getId());
        return dto;
    }
}
