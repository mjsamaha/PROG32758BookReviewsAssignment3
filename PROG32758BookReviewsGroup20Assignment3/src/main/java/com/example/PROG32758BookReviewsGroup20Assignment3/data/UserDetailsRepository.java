package com.example.PROG32758BookReviewsGroup20Assignment3.data;

import com.example.PROG32758BookReviewsGroup20Assignment3.models.MyUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<MyUserDetails, Integer> {
    MyUserDetails findByUsername(String username);
}
