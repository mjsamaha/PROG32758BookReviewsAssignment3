package controllers;

import models.Reviews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.ReviewService;

// @Controller
// I was using @Controllers and not @RestControllers
// Removed to ensure it's simple
@Controller
@RequestMapping("/reviews")
public class ReviewsController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewsController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // View reviews for a specific book
    @GetMapping("/reviews/{bookId}")
    public String viewReviews(@PathVariable Long bookId, Model model) {
        model.addAttribute("reviews", reviewService.getReviewsByBookId(bookId));
        return "list"; // Return the template for listing reviews
    }

    // Submit a review for a book
    @PostMapping("/reviews/{bookId}/add")
    public String addReview(@PathVariable Long bookId, @ModelAttribute("review") Reviews review) {
        reviewService.addReviewToBook(bookId, review);
        return "redirect:/books"; // Redirect to the books list after adding a review
    }

}
