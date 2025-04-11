package com.example.PROG32758BookReviewsGroup20Assignment3.service;

import com.example.PROG32758BookReviewsGroup20Assignment3.data.BookRepository;
import com.example.PROG32758BookReviewsGroup20Assignment3.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    // find all books
    public List<Book> getAllBooks(){
        return (List<Book>) bookRepository.findAll();
    }

//    // fetch books based on Author's bookId
//    public List<Book> findByBookId(Long bookId){
//        return bookRepository.findByBookId(bookId);
//    }

    // Save book
    public void saveBook(Book book){
        bookRepository.save(book);
    }



    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id);
    }

    // Optional deleteBook method
    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }
}
