package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.BookService;
import service.ReviewService;

@Controller
public class BookController {

    BookService bookService;
    ReviewService reviewService;

    @Autowired
    public BookController(BookService bookService, ReviewService reviewService) {
        this.bookService = bookService;
        this.reviewService = reviewService;
    }








}
