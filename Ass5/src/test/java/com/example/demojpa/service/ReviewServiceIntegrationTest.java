package com.example.demojpa.service;

import com.example.demojpa.BaseIntegrationTest;
import com.example.demojpa.dto.ReviewDTO;
import com.example.demojpa.entity.User;
import com.example.demojpa.entity.Product;
import com.example.demojpa.repository.ReviewRepository;
import com.example.demojpa.repository.UserRepository;
import com.example.demojpa.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

//@Disabled("Disabled due to missing Docker/Testcontainers environment")
//@SpringBootTest
//@Transactional
//class ReviewServiceIntegrationTest extends BaseIntegrationTest {
//    @Autowired
//    private ReviewService reviewService;
//    @Autowired
//    private ReviewRepository reviewRepository;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private ProductRepository productRepository;
//
//    private User user;
//    private Product product;
//
//    @BeforeEach
//    void setup() {
//        user = new User();
//        user.setUsername("reviewer");
//        user.setEmail("reviewer@example.com");
//        user = userRepository.save(user);
//        product = new Product();
//        product.setName("product1");
//        product.setPrice(100.0);
//        product = productRepository.save(product);
//    }
//
//    @Test
//    void testCreateAndGetReview() {
//        ReviewDTO dto = new ReviewDTO();
//        dto.setRating(5);
//        dto.setComment("Great!");
//        dto.setUserId(user.getId());
//        dto.setProductId(product.getId());
//        ReviewDTO saved = reviewService.createReview(dto);
//        assertNotNull(saved.getId());
//        Optional<ReviewDTO> found = reviewService.getReviewById(saved.getId());
//        assertTrue(found.isPresent());
//        assertEquals(5, found.get().getRating());
//        assertEquals("Great!", found.get().getComment());
//        assertEquals(user.getId(), found.get().getUserId());
//        assertEquals(product.getId(), found.get().getProductId());
//    }
//
//    @Test
//    void testUpdateReview() {
//        ReviewDTO dto = new ReviewDTO();
//        dto.setRating(3);
//        dto.setComment("Ok");
//        dto.setUserId(user.getId());
//        dto.setProductId(product.getId());
//        ReviewDTO saved = reviewService.createReview(dto);
//        ReviewDTO update = new ReviewDTO();
//        update.setRating(4);
//        update.setComment("Better");
//        update.setUserId(user.getId());
//        update.setProductId(product.getId());
//        Optional<ReviewDTO> updated = reviewService.updateReview(saved.getId(), update);
//        assertTrue(updated.isPresent());
//        assertEquals(4, updated.get().getRating());
//        assertEquals("Better", updated.get().getComment());
//    }
//
//    @Test
//    void testDeleteReview() {
//        ReviewDTO dto = new ReviewDTO();
//        dto.setRating(2);
//        dto.setComment("Bad");
//        dto.setUserId(user.getId());
//        dto.setProductId(product.getId());
//        ReviewDTO saved = reviewService.createReview(dto);
//        Long id = saved.getId();
//        reviewService.deleteReview(id);
//        assertFalse(reviewService.getReviewById(id).isPresent());
//    }
//}
