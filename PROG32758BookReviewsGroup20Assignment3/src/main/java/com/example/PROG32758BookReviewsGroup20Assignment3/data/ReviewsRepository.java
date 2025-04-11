package com.example.PROG32758BookReviewsGroup20Assignment3.data;

import com.example.PROG32758BookReviewsGroup20Assignment3.models.Reviews;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends CrudRepository<Reviews, Long> {
    // Custom query method to find reviews by book ID
    List<Reviews> findAllByBookId(Long bookId);

}
