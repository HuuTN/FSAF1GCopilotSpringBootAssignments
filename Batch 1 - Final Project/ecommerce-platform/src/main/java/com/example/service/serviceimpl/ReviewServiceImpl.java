package com.example.service.serviceimpl;

import com.example.model.entity.Review;
import com.example.model.entity.Product;
import com.example.model.entity.Customer;
import com.example.repository.ReviewRepository;
import com.example.repository.ProductRepository;
import com.example.repository.CustomerRepository;
import com.example.service.ReviewService;
import com.example.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
    
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    
    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, 
                           ProductRepository productRepository, 
                           CustomerRepository customerRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }
    
    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
    
    @Override
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }
    
    @Override
    public List<Review> getReviewsByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }
    
    @Override
    public List<Review> getReviewsByCustomerId(Long customerId) {
        return reviewRepository.findByCustomerId(customerId);
    }
    
    @Override
    public List<Review> getReviewsByRating(Integer rating) {
        return reviewRepository.findByRating(rating);
    }
    
    @Override
    public Review createReview(Long productId, Long customerId, Integer rating, String reviewContent) {
        // Check if customer has already reviewed this product
        if (hasCustomerReviewedProduct(customerId, productId)) {
            throw new IllegalArgumentException("Customer has already reviewed this product");
        }
        
        // Find product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
        
        // Find customer
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));
        
        // Create review
        Review review = new Review();
        review.setProduct(product);
        review.setCustomer(customer);
        review.setRating(rating);
        review.setReviewContent(reviewContent);
        
        return reviewRepository.save(review);
    }
    
    @Override
    public Review updateReview(Long id, Integer rating, String reviewContent) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));
        
        existingReview.setRating(rating);
        existingReview.setReviewContent(reviewContent);
        
        return reviewRepository.save(existingReview);
    }
    
    @Override
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));
        reviewRepository.delete(review);
    }
    
    @Override
    public Double getAverageRatingByProductId(Long productId) {
        return reviewRepository.findAverageRatingByProductId(productId);
    }
    
    @Override
    public Long getReviewCountByProductId(Long productId) {
        return reviewRepository.countReviewsByProductId(productId);
    }
    
    @Override
    public List<Review> getReviewsByProductIdSortedByDate(Long productId) {
        return reviewRepository.findByProductIdOrderByCreatedAtDesc(productId);
    }
    
    @Override
    public List<Review> getReviewsByCustomerIdSortedByDate(Long customerId) {
        return reviewRepository.findByCustomerIdOrderByCreatedAtDesc(customerId);
    }
    
    @Override
    public boolean hasCustomerReviewedProduct(Long customerId, Long productId) {
        List<Review> existingReviews = reviewRepository.findByProductIdAndCustomerId(productId, customerId);
        return !existingReviews.isEmpty();
    }
}
