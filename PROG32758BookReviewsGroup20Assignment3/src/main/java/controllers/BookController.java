package controllers;

import models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.BookService;

@Controller//
// I was using @Controllers and not @RestControllers
// Removed to ensure it's simple
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

    // View all books (public)
    @GetMapping // removed the ("books") for brevity
    public String viewBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "list";
    }

    // Add book form (protected)
    @GetMapping("/add") // switched from books/add to just /add
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }


    // Save book (protected)
    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("book") Book book) {
        if (book == null) {
            // Log an error or throw an exception
            System.out.println("Book object is null!");
            return "error"; // Redirect to an error page or handle accordingly
        }
        bookService.saveBook(book);
        return "redirect:/books";
    }

    /*
    I could add Spring features like @Valid and Binding result for validation...
     */


}
