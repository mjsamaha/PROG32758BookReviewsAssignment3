package service;

import data.BookRepository;
import models.Book;
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

    // fetch books based on Author's bookId
    public List<Book> findByBookId(Long bookId){
        return bookRepository.findByBookId(bookId);
    }

    public void saveBook(Book book){
        bookRepository.save(book);
    }

    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id);
    }

    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }
}
