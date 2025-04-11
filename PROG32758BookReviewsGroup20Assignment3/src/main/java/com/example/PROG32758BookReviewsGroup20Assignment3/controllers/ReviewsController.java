package com.example.PROG32758BookReviewsGroup20Assignment3.controllers;

import com.example.PROG32758BookReviewsGroup20Assignment3.models.Book;
import com.example.PROG32758BookReviewsGroup20Assignment3.models.Reviews;
import com.example.PROG32758BookReviewsGroup20Assignment3.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.PROG32758BookReviewsGroup20Assignment3.service.ReviewService;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/reviews")
public class ReviewsController {

    private final ReviewService reviewService;
    private final BookService bookService;

    @Autowired
    public ReviewsController(ReviewService reviewService, BookService bookService) {
        this.reviewService = reviewService;
        this.bookService = bookService;
    }


    // Thymeleaf approach, load for a specific book's details and reviews
    @GetMapping("/{bookId}/fragment")
    public String getBookDetailsFragment(@PathVariable Long bookId, Model model) {
        Book book = bookService.getBookById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + bookId));
        List<Reviews> reviews = reviewService.getReviewsByBookId(bookId);

        model.addAttribute("book", book); // Add book to the model
        model.addAttribute("reviews", reviews); // Add reviews to the model
        return "bookDetailsFragment"; // Return the Thymeleaf fragment
    }


    // add a review for a book
    @PostMapping("/{bookId}/add")
    public String addReview(@PathVariable Long bookId, @ModelAttribute("review") Reviews review) {
        reviewService.addReviewToBook(bookId, review); // Link and save the review
        // Redirecting back to the list since dynamic reloading isn't handled here
        return "redirect:/books/view"; // Redirects to the books list
    }



}
