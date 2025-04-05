package service;

import models.Reviews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewService reviewService;

    // save a review for a book
    public void saveReview(Reviews review){
        reviewService.saveReview(review);
    }

    // delete a review for a book
    public void deleteReviewById(Long id){
        reviewService.deleteReviewById(id);
    }
}
