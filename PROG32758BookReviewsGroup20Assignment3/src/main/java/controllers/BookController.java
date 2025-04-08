package controllers;

import models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import service.BookService;

import java.util.List;

@Controller
// Removed to ensure it's simple
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
    @GetMapping("/books") // Simplified, directly maps to "/books"
    public String viewBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "list"; // Corresponding to list.html in templates folder
    }


    // Serve "Add a Book" form, restricted to authenticated users
    @GetMapping("/addBook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addBook"; // Corresponding to addBook.html in templates folder
    }

    // Handle form submission for adding a book
    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("book") Book book, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/denied"; // Redirect to "denied" if user is not authenticated
        }
        bookService.saveBook(book); // Save the book using the service
        return "redirect:/books"; // Redirect to the list of books
    }

}
