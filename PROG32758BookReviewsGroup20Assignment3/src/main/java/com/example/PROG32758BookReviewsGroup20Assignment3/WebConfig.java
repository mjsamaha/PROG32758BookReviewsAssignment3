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
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**") // Example for static files
                .addResourceLocations("classpath:/static/");
    }
}
