package com.example.demo;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.List;

public class SuperUserAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // get username check user is super-user
        String name = (String) authentication.getPrincipal();
        if ("super-user".equalsIgnoreCase(name)) {
            Users users = new Users();
            users.setFirstName("Super-User");
            users.setEmail("super-user@gmail.com");
            return UsernamePasswordAuthenticationToken.authenticated(users, null, List.of(() -> "SUPER-USER"));
        } else {
            // when null is returned provider will try to authenticate with different Provider
            // in this case DaoAuthenticationProvider
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
