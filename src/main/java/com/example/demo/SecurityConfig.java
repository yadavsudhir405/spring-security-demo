package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails foo = User.withUsername("foo").password("{noop}foo").roles("pilot").build();
        UserDetails bar = User.withUsername("bar").password("{noop}bar").roles("pilot").build();
        UserDetails flightController = User.withUsername("flightController").password("{noop}flightController").roles("flightController").build();

        return new InMemoryUserDetailsManager(foo, bar, flightController);
    }
}
