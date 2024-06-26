package com.maitreya.firstjobapp.review;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ReviewService {
    boolean addReview(Long companyId, Review review);

    List<Review> getAllReviews(Long companyId);

    Review getReviewById(Long companyId, Long reviewId);

    boolean updateReviewById(Long companyId, Long reviewId, Review updatedReview);

    boolean deleteReviewById(Long companyId, Long reviewId);
}
