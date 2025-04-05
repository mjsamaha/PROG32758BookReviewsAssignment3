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

    /*
        @RequestMapping("/books")
        .getAllBooks()

        Mapping to get all books

        return "books.html"
     */

    /*
        @RequestMapping("/books/{id}")
        .getBookById(id).orElse(null);

        Mapping to get book by id

        return "bookDetails.html" --> change later to go with AJAX/JS
     */

    /*
        @RequestMapping("/addBook")
        addBook

        Mapping to add a book using form

        return back to index page
     */

    /*
        @RequestMapping("/save")
        saveBook

        Mapping to save a newly created book

        redirect back to index
     */

    /*
        @RequestMapping("/deleteBook/{id}")
        deleteBook

        Mapping to delete a book by its ID

        return back to index page
     */












}
