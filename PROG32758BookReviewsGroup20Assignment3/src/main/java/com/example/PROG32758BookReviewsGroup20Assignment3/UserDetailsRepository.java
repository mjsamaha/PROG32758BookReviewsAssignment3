package com.example.PROG32758BookReviewsGroup20Assignment3;

import org.springframework.data.repository.CrudRepository;

public interface UserDetailsRepository extends CrudRepository<MyUserDetails, Long> {
    public MyUserDetails findByUsername(String username);
}
