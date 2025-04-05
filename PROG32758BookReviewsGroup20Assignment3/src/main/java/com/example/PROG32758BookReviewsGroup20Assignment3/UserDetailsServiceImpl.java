package com.example.PROG32758BookReviewsGroup20Assignment3;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    private UserDetailsRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUserDetails mud = userRepository.findByUsername(username);
        if (mud == null) throw new UsernameNotFoundException("User not found");
        return mud;
    }
}
