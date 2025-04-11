package com.example.PROG32758BookReviewsGroup20Assignment3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /*
    User Creation: [matthew] [sahil] [sebastian] [guest]
        matthew,sahil,sebastian will have ROOT access
        guest will have normal user privileges
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // UserDetails for matthew
        UserDetails matthew = User.withUsername("matthew")
                .password(passwordEncoder().encode("pass"))
                .roles("ADMIN")
                .build();

        // UserDetails for sahil: added by


        // UserDetails for sebastian: added by

        UserDetails guest = User.withUsername("guest")
                .password(passwordEncoder().encode("pass"))
                .roles("USER")
                .build();

        // once you add userDetails, you need to return new matthew, sahil, sebastian, etc.
        return new InMemoryUserDetailsManager(matthew, guest);
    }

    // Authorization -- Security Filter Chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(csrf -> csrf.disable())
                // Allow H2 console access (use proper security in production)
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
                // Authorization rules
                .authorizeHttpRequests(auth -> auth
                        // restrict access until user authenticates with login
                        .requestMatchers("/", "/books", "/resources/**").permitAll()
                        // Restricted pages
                        .requestMatchers("/books/add", "/saveBook").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/books/view", "/deleteBook").hasRole("ADMIN")
                        // All other pages require authentication
                        .anyRequest().authenticated()
                )
                // Use Spring Security's default login page
                .formLogin(form -> form
                        .defaultSuccessUrl("/", true) // Redirect to home or previously requested page
                        .permitAll()
                )
                // Logout configuration
                .logout(logout -> logout
                        .logoutUrl("/logout") // Default logout URL
                        .logoutSuccessUrl("/") // Redirect to home after logout
                        .permitAll()
                )
                // Unauthorized access handling
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/denied") // Redirect to custom 403 page
                );


        // NEVER HAD THIS LINE implemented into the code -- maybe this fixes my mapping errors
        /*
        http.csrf((csrf) -> csrf.disable());
        http.headers((headers) -> headers.frameOptions((frame) -> frame.sameOrigin()));
        */
        return http.build();
    }

    // Auth manager
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(authenticationProvider);
    }


}
