package com.example.demo;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;

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
     return username -> new UserDetails() {
         @Override
         public Collection<? extends GrantedAuthority> getAuthorities() {
             return null;
         }

         @Override
         public String getPassword() {
             return "{noop}bar";
         }

         @Override
         public String getUsername() {
             return "foo";
         }

         @Override
         public boolean isAccountNonExpired() {
             return true;
         }

         @Override
         public boolean isAccountNonLocked() {
             return true;
         }

         @Override
         public boolean isCredentialsNonExpired() {
             return true;
         }

         @Override
         public boolean isEnabled() {
             return true;
         }
     };
    }
}
