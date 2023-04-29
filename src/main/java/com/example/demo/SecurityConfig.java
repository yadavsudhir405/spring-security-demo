package com.example.demo;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeRequests(
                        configurer -> {
                            configurer.antMatchers("/favicon.ico").permitAll();
                            configurer.antMatchers("/public").permitAll();
                            configurer.anyRequest().authenticated();
                        }
                )
                .formLogin(withDefaults())
                .build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("foo")
                        .password("{noop}foo") // without bcrypt or noop password as foo won't work
                        .authorities("ADMIN")
                        .build()
        );
    }
}
