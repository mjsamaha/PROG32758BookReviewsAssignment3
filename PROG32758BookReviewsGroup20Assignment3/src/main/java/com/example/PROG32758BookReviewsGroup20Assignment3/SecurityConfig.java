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
        http
                .csrf(csrf -> csrf.disable()) // Simplified; disable CSRF for demo purposes
                .authorizeHttpRequests(auth -> auth
                        // Public pages
                        .requestMatchers("/", "/books", "/static/**").permitAll()
                        // Restricted pages
                        .requestMatchers("/books/add", "/saveBook").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                // Use Spring's default login page
                .formLogin(form -> form
                        .defaultSuccessUrl("/", true) // Redirects to previously requested page or home
                        .permitAll()
                )
                .logout(logout -> logout.permitAll())
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/denied")); // Unauthorized access to /denied

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
