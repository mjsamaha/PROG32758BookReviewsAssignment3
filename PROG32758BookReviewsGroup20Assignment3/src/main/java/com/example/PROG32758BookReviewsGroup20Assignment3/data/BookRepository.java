package com.example.PROG32758BookReviewsGroup20Assignment3.data;

import com.example.PROG32758BookReviewsGroup20Assignment3.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
  //  List<Book> findByBookId(Long bookId);
}
