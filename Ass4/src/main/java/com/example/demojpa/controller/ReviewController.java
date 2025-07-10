package com.example.demojpa.controller;

import com.example.demojpa.dto.ReviewDTO;
import com.example.demojpa.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Operation(summary = "Get all reviews", description = "Get paginated list of all reviews")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of reviews returned successfully")
    })
    @GetMapping
    public Page<ReviewDTO> getAllReviews(Pageable pageable) {
        return reviewService.getAllReviews(pageable);
    }

    @Operation(summary = "Get review by ID", description = "Get a review by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Review found"),
        @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create review", description = "Create a new review")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Review created successfully")
    })
    @PostMapping
    public ReviewDTO createReview(@RequestBody ReviewDTO dto) {
        return reviewService.createReview(dto);
    }

    @Operation(summary = "Update review", description = "Update an existing review by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Review updated successfully"),
        @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long id, @RequestBody ReviewDTO dto) {
        return reviewService.updateReview(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete review", description = "Delete a review by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Review deleted successfully")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
