package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
@Configuration
public class AppConfig implements WebMvcConfigurer {
    private UserMethodArgumentResolver userMethodArgumentResolver;

    public AppConfig(UserMethodArgumentResolver userMethodArgumentResolver) {
        this.userMethodArgumentResolver = userMethodArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
       resolvers.add(this.userMethodArgumentResolver);
    }
}
