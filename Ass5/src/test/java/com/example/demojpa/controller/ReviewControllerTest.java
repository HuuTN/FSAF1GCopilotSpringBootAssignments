package com.example.demojpa.controller;

import com.example.demojpa.dto.ReviewDTO;
import com.example.demojpa.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReviewService reviewService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /api/v1/reviews returns paginated list of reviews")
    void getAllReviews() throws Exception {
        Mockito.when(reviewService.getAllReviews(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(new ReviewDTO())));
        mockMvc.perform(get("/api/v1/reviews"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("content").exists());
    }

    @Test
    @DisplayName("GET /api/v1/reviews/{id} returns review if found")
    void getReviewById_found() throws Exception {
        Mockito.when(reviewService.getReviewById(1L)).thenReturn(Optional.of(new ReviewDTO()));
        mockMvc.perform(get("/api/v1/reviews/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    @DisplayName("GET /api/v1/reviews/{id} returns 404 if not found")
    void getReviewById_notFound() throws Exception {
        Mockito.when(reviewService.getReviewById(99L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/v1/reviews/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /api/v1/reviews creates review")
    void createReview() throws Exception {
        ReviewDTO request = new ReviewDTO();
        ReviewDTO response = new ReviewDTO();
        Mockito.when(reviewService.createReview(any(ReviewDTO.class))).thenReturn(response);
        mockMvc.perform(post("/api/v1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    @DisplayName("PUT /api/v1/reviews/{id} updates review if found")
    void updateReview_found() throws Exception {
        ReviewDTO request = new ReviewDTO();
        ReviewDTO response = new ReviewDTO();
        Mockito.when(reviewService.updateReview(eq(1L), any(ReviewDTO.class))).thenReturn(Optional.of(response));
        mockMvc.perform(put("/api/v1/reviews/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    @DisplayName("PUT /api/v1/reviews/{id} returns 404 if not found")
    void updateReview_notFound() throws Exception {
        ReviewDTO request = new ReviewDTO();
        Mockito.when(reviewService.updateReview(eq(99L), any(ReviewDTO.class))).thenReturn(Optional.empty());
        mockMvc.perform(put("/api/v1/reviews/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /api/v1/reviews/{id} deletes review")
    void deleteReview() throws Exception {
        Mockito.doNothing().when(reviewService).deleteReview(1L);
        mockMvc.perform(delete("/api/v1/reviews/1"))
                .andExpect(status().isNoContent());
    }
}
