package com.example.demo;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.RobotAuthenticationToken.authenticated;

public class RobotAuthenticationProvider implements AuthenticationProvider {


    private final List<String> passwords = new ArrayList<>();
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       // String username = (String) authentication.getPrincipal();
        Object password = authentication.getPrincipal();
        if (this.passwords.contains(password)) {
            Users users = new Users();
            users.setFirstName("ROBOT");
            users.setEmail("robot@gmail.com");
            return authenticated(users, null, List.of(() -> "ROBOT"));
        }else {
            throw new BadCredentialsException("Bad Credential ⛔️");
        }

    }

    RobotAuthenticationProvider passwords(String pwd) {
        this.passwords.add(pwd);
        return this;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(RobotAuthenticationToken.class);
    }
}
