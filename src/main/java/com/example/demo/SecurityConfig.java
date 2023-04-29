package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.List;

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
    public UserDetailsService userDetails(UserService userService) {
        return username -> {
            Users user = userService.findByFirstName(username);
            return new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return List.of(user::getRole);
                }

                @Override
                public String getPassword() {
                    return "{noop}"+user.getPassword();
                }

                @Override
                public String getUsername() {
                    return user.getFirstName();
                }

                @Override
                public boolean isAccountNonExpired() {
                    return !user.getIsAccountExpired();
                }

                @Override
                public boolean isAccountNonLocked() {
                    return !user.getIsAccountLocked();
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return !user.getIsPasswordExpired();
                }

                @Override
                public boolean isEnabled() {
                    return true;
                }
            };
        };
    }


}
