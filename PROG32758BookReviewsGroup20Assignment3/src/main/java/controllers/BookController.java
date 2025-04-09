package controllers;

import models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import service.BookService;

import java.util.List;
@Controller//
// I was using @Controllers and not @RestControllers

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
    @GetMapping("/books") // Maps to "/books"
    public String viewBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "list"; // Corresponding to list.html in templates folder
    }

    // Serve "Add a Book" form, restricted to authenticated users
    @GetMapping("/books/add") // Prefer structured route
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book()); // Provide an empty Book object
        return "addBook"; // Render addBook.html
    }

    // Handle form submission for adding a book
    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book); // Save the book using a service
        return "redirect:/books"; // Redirect to the books list after saving
    }
}
