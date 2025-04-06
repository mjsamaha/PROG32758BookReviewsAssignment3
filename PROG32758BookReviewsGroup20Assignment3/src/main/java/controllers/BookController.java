package controllers;

import models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.BookService;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    /*
    Requirements:
    - add Mapping to serve both listing and adding books
    - map saving a book
    - mark the "Add A Book" view to be only accessible to auth. users
     */

    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // View list of all books
    @GetMapping
    public String viewBooks(Model model){
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    // Serve "Add a Book form, restricted to auth users
    @GetMapping("/add")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        return "books/add";
    }

    // Handle form submission for adding a book
    @PostMapping("/save")
    public String saveBook(@ModelAttribute ("book") Book book, Authentication authentication){
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/denied";
        }
        bookService.saveBook(book); // Call service to save book
        return "redirect:/books"; // Redirect to the list of books
    }


}
