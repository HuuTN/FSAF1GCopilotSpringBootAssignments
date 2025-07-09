package com.example.demojpa.service;

import com.example.demojpa.dto.ReviewDTO;
import com.example.demojpa.entity.Review;
import com.example.demojpa.entity.Product;
import com.example.demojpa.entity.Customer;
import com.example.demojpa.repository.ReviewRepository;
import com.example.demojpa.repository.ProductRepository;
import com.example.demojpa.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Page<ReviewDTO> getAllReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable).map(this::toDTO);
    }

    public Optional<ReviewDTO> getReviewById(Long id) {
        return reviewRepository.findById(id).map(this::toDTO);
    }

    public ReviewDTO createReview(ReviewDTO dto) {
        Review review = new Review();
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        if (dto.getProductId() != null) {
            productRepository.findById(dto.getProductId()).ifPresent(review::setProduct);
        }
        if (dto.getCustomerId() != null) {
            customerRepository.findById(dto.getCustomerId()).ifPresent(review::setCustomer);
        }
        return toDTO(reviewRepository.save(review));
    }

    public Optional<ReviewDTO> updateReview(Long id, ReviewDTO dto) {
        return reviewRepository.findById(id).map(review -> {
            review.setRating(dto.getRating());
            review.setComment(dto.getComment());
            if (dto.getProductId() != null) {
                productRepository.findById(dto.getProductId()).ifPresent(review::setProduct);
            }
            if (dto.getCustomerId() != null) {
                customerRepository.findById(dto.getCustomerId()).ifPresent(review::setCustomer);
            }
            return toDTO(reviewRepository.save(review));
        });
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    private ReviewDTO toDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        if (review.getProduct() != null) dto.setProductId(review.getProduct().getId());
        if (review.getCustomer() != null) dto.setCustomerId(review.getCustomer().getId());
        return dto;
    }
}
