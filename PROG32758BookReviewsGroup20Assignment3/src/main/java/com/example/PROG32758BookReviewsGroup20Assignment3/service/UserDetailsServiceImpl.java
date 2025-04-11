package com.example.PROG32758BookReviewsGroup20Assignment3.service;


import com.example.PROG32758BookReviewsGroup20Assignment3.data.UserDetailsRepository;
import com.example.PROG32758BookReviewsGroup20Assignment3.models.MyUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userRepository;

    // Constructor to inject UserDetailsRepository
    public UserDetailsServiceImpl(UserDetailsRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUserDetails mud = userRepository.findByUsername(username);
        if (mud == null) throw new UsernameNotFoundException("User not found");
        return mud;
    }
}
