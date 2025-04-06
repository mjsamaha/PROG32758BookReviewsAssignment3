package service;

import data.ReviewsRepository;
import models.Reviews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    public ReviewsRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewsRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // Sahil: Fetch all reviews for a specific book by its ID
    public List<Reviews> getReviewsByBookId(Long bookId) {
        // Use repository method to retrieve all reviews for the specified book
        return reviewRepository.findAllByBookId(bookId);
    }

    // Sahil: Add a review to an existing book
    public void addReviewToBook(Long bookId, Reviews review) {
        // Link the review to the book via bookId
        review.setBookId(bookId);

        // Save the review to the database
        reviewRepository.save(review);
    }


}
