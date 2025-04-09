package com.example.PROG32758BookReviewsGroup20Assignment3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"models"}) // Add this if models are not discovered.
public class Prog32758BookReviewsGroup20Assignment3Application {

	public static void main(String[] args) {
		SpringApplication.run(Prog32758BookReviewsGroup20Assignment3Application.class, args);
	}

}
