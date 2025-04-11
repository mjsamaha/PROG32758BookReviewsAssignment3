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
    @GetMapping("/view") // removed the ("books") for brevity
    public String viewBooks(Model model) {
        System.out.println("Should direct to view books!");
        model.addAttribute("books", bookService.getAllBooks());
        return "list";
    }

    // Add book form (protected)
    @GetMapping("/add") // switched from books/add to just /add
    public String addBook(Model model) {
        System.out.println("Should direct to addBook form!");
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
        return "redirect:/books/view"; // changed; correct mapping to view books
    }

    /*
    I could add Spring features like @Valid and Binding result for validation...
     */

    /*
    Simple CURLs to debug requests
    curl -i -X GET http://localhost:8080
    HTTP/1.1 200
    X-Content-Type-Options: nosniff
    X-XSS-Protection: 0
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Pragma: no-cache
    Expires: 0
    Content-Type: text/html;charset=UTF-8
    Content-Language: en-CA
    Transfer-Encoding: chunked
    Date: Fri, 11 Apr 2025 13:02:44 GMT

    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Home</title>
    ..etc>



     */

    /* Error Logging in Terminal
    ===== Before logging in =====
    ### When accessing addBook link: ###

    Securing GET /books/add
    Set SecurityContextHolder to anonymous SecurityContext
    1. Saved request http://localhost:8080/books/add?continue to session
    2. Redirecting to http://localhost:8080/login
    Securing GET /login

    ===== After logging in =====
    ### When accessing addBook link: ###
    - Seems like the /books/add request is failing, and directing to the custom error page I made.

    Securing GET /books/add
    Retrieved SecurityContextImpl [Authentication=UsernamePasswordAuthenticationToken [Principal=org.springframework.security.core.userdetails.User [Username=matthew, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, CredentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[ROLE_ADMIN]], Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=5B2B9C0E02E9AB4A1635304985D59EE3], Granted Authorities=[ROLE_ADMIN]]]
    Secured GET /books/add
    GET "/books/add", parameters={}
    Mapped to ResourceHttpRequestHandler [classpath [META-INF/resources/], classpath [resources/], classpath [static/], classpath [public/], ServletContext [/]]
    Opening JPA EntityManager in OpenEntityManagerInViewInterceptor
    Resource not found
    Resolved [org.springframework.web.servlet.resource.NoResourceFoundException: No static resource books/add.]
    Closing JPA EntityManager in OpenEntityManagerInViewInterceptor
    Completed 404 NOT_FOUND
    Securing GET /error
    Retrieved SecurityContextImpl [Authentication=UsernamePasswordAuthenticationToken [Principal=org.springframework.security.core.userdetails.User [Username=matthew, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, CredentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[ROLE_ADMIN]], Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=5B2B9C0E02E9AB4A1635304985D59EE3], Granted Authorities=[ROLE_ADMIN]]]
    Secured GET /error
    "ERROR" dispatch for GET "/error", parameters={}
    Mapped to org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController#errorHtml(HttpServletRequest, HttpServletResponse)
    Opening JPA EntityManager in OpenEntityManagerInViewInterceptor
    Selected 'text/html' given [text/html, text/html;q=0.8]
    Closing JPA EntityManager in OpenEntityManagerInViewInterceptor
    Exiting from "ERROR" dispatch, status 404


     */


}
