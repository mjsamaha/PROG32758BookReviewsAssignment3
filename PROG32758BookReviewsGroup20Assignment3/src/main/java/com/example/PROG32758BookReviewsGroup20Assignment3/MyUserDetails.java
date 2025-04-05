package com.example.PROG32758BookReviewsGroup20Assignment3;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MyUserDetails implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    private String username;
    private String password;
    private String roles;


}
