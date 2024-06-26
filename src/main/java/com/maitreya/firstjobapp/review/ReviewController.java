package com.maitreya.firstjobapp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // ADD A NEW REVIEW FOR A COMPANY
    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review) {
        boolean isReviewAdded = reviewService.addReview(companyId, review);
        if (isReviewAdded)
            return new ResponseEntity<>("Review added successfully!", HttpStatus.CREATED);

        return new ResponseEntity<>("Company not found!", HttpStatus.NOT_FOUND);
    }

    // GET ALL REVIEWS FOR A COMPANY
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        List<Review> reviews = reviewService.getAllReviews(companyId);
        if (!reviews.isEmpty())
            return new ResponseEntity<>(reviews, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET A SPECIFIC REVIEW FOR A COMPANY
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.getReviewById(companyId, reviewId);
        if (review != null)
            return new ResponseEntity<>(review, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // UPDATE A SPECIFIC REVIEW FOR A COMPANY
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReviewById(@PathVariable Long companyId, @PathVariable Long reviewId,
                                                   @RequestBody Review updatedReview) {
        boolean isReviewUpdated = reviewService.updateReviewById(companyId, reviewId, updatedReview);
        if (isReviewUpdated)
            return new ResponseEntity<>("Review updated successfully!", HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE A SPECIFIC REVIEW FOR A COMPANY
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        boolean isReviewDeleted = reviewService.deleteReviewById(companyId, reviewId);
        if (isReviewDeleted)
            return new ResponseEntity<>("Review deleted successfully!", HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
