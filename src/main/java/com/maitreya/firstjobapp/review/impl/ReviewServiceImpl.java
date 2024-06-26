package com.maitreya.firstjobapp.review.impl;

import com.maitreya.firstjobapp.company.Company;
import com.maitreya.firstjobapp.company.CompanyService;
import com.maitreya.firstjobapp.review.Review;
import com.maitreya.firstjobapp.review.ReviewRepository;
import com.maitreya.firstjobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReviewById(Long companyId, Long reviewId, Review updatedReview) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null && reviewRepository.existsById(reviewId)) {
            updatedReview.setCompany(company);
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteReviewById(Long companyId, Long reviewId) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null && reviewRepository.existsById(reviewId)) {
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company currentCompany = review.getCompany();
            currentCompany.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompanyById(companyId, currentCompany);
            reviewRepository.deleteById(reviewId);
            return true;
        }

        return false;
    }
}