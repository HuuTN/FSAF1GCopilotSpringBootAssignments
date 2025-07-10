package com.example.demojpa.service;

import com.example.demojpa.dto.ReviewDTO;
import com.example.demojpa.entity.Review;
import com.example.demojpa.repository.ReviewRepository;
import com.example.demojpa.repository.ProductRepository;
import com.example.demojpa.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewServiceTest {
    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllReviews() {
        Review review = new Review();
        review.setId(1L);
        Page<Review> page = new PageImpl<>(Collections.singletonList(review));
        when(reviewRepository.findAll(any(PageRequest.class))).thenReturn(page);
        Page<ReviewDTO> result = reviewService.getAllReviews(PageRequest.of(0, 10));
        assertEquals(1, result.getTotalElements());
        assertEquals(1L, result.getContent().get(0).getId());
    }

    @Test
    void testGetReviewById_found() {
        Review review = new Review();
        review.setId(1L);
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        Optional<ReviewDTO> result = reviewService.getReviewById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testGetReviewById_notFound() {
        when(reviewRepository.findById(2L)).thenReturn(Optional.empty());
        Optional<ReviewDTO> result = reviewService.getReviewById(2L);
        assertFalse(result.isPresent());
    }

    @Test
    void testCreateReview() {
        ReviewDTO dto = new ReviewDTO();
        dto.setRating(5);
        dto.setComment("Good");
        Review review = new Review();
        review.setId(1L);
        when(reviewRepository.save(any(Review.class))).thenReturn(review);
        ReviewDTO result = reviewService.createReview(dto);
        assertEquals(1L, result.getId());
    }

    @Test
    void testUpdateReview_found() {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(1L);
        Review review = new Review();
        review.setId(1L);
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);
        Optional<ReviewDTO> result = reviewService.updateReview(1L, dto);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testUpdateReview_notFound() {
        ReviewDTO dto = new ReviewDTO();
        when(reviewRepository.findById(2L)).thenReturn(Optional.empty());
        Optional<ReviewDTO> result = reviewService.updateReview(2L, dto);
        assertFalse(result.isPresent());
    }

    @Test
    void testDeleteReview_found() {
        Review review = new Review();
        review.setId(1L);
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        doNothing().when(reviewRepository).deleteById(1L);
        assertDoesNotThrow(() -> reviewService.deleteReview(1L));
    }

    @Test
    void testDeleteReview_notFound() {
        when(reviewRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> reviewService.deleteReview(2L));
    }
}
