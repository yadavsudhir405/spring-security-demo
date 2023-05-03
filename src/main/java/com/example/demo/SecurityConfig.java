package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        ProviderManager providerManager = new ProviderManager(List.of(new RobotAuthenticationProvider().passwords("beep-beep")));
        return httpSecurity
                .authorizeRequests(
                        configurer -> {
                            configurer.antMatchers("/favicon.ico").permitAll();
                            configurer.antMatchers("/public").permitAll();
                            configurer.anyRequest().authenticated();
                        }
                )
                .authenticationProvider(new SuperUserAuthenticationProvider())
                .authenticationProvider(new RobotAuthenticationProvider().passwords("beep-beep"))
                .addFilterBefore(new RobotAuthenticationFilter(providerManager), FilterSecurityInterceptor.class)
                .formLogin(withDefaults())
                .oauth2Login(withDefaults())
                .build();
    }

    @Bean
    public UserDetailsService userDetails(UserService userService) {
        return userService::findByFirstName;
    }


}
