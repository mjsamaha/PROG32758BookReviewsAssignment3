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

import static org.springframework.security.config.Customizer.withDefaults;

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

        // UserDetails for sahil


        // UserDetails for sebastian

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
        http.exceptionHandling(x -> x.accessDeniedPage("/denied"));

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/books/add").authenticated()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/everyone").permitAll()
                        // DELETE BELOW AFTER DEBUGGING TO FOLLOW REQUIREMENTS
                        /*
                        Must be as:
                        .requestMatchers("/books/add", "/admin").authenticated()
                        .requestMatchers("/").permitAll()
                        .anyRequest().permitAll()
                         */
                        .requestMatchers("/**").permitAll() // for debugging purposes, getting errors when I want to add books or list books
                        .anyRequest().authenticated())
                .httpBasic(withDefaults())
                .formLogin(form -> form
                        .defaultSuccessUrl("/", true)
                        .permitAll());

        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }

    // AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(authenticationProvider);
    }
}
