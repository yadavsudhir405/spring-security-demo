package com.example.demo;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.ArrayList;
import java.util.List;

public class RobotLoginConfigurer extends AbstractHttpConfigurer<RobotLoginConfigurer, HttpSecurity> {
    private final List<String> passwords = new ArrayList<>();

    @Override
    public void init(HttpSecurity httpSecurity) throws Exception {
       httpSecurity.authenticationProvider(new RobotAuthenticationProvider(this.passwords));

    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManager providerManager = httpSecurity.getSharedObject(AuthenticationManager.class);
        httpSecurity.addFilterBefore(new RobotAuthenticationFilter(providerManager), FilterSecurityInterceptor.class);
    }

    public RobotLoginConfigurer passwords(String password) {
        this.passwords.add(password);
        return this;
    }
}
