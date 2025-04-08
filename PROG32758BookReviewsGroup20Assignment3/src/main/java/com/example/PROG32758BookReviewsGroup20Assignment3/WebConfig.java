package com.example.PROG32758BookReviewsGroup20Assignment3;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /*
    I'm having conflicts.. /books/** is treated as a request for the controller and not a static controller.
    The WebConfig will handle conflicts between static resources and controller mappings
    Ensures that /static/** resolves under /static/
     */
    // https://stackoverflow.com/questions/24661289/spring-boot-not-serving-static-content

    /*
    Fix; since I removed the /books and /reviews from the controllers, there is no need for this class
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve only static resources located in /static
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(3600); // caching for static files
    }
}
