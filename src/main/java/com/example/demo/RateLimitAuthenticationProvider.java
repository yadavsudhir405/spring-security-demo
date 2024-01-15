package com.example.demo;

import lombok.val;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimitAuthenticationProvider implements AuthenticationProvider {

    private final Map<String, Instant> cache = new ConcurrentHashMap<>();

    private final AuthenticationProvider parentAuthProvider;


    public RateLimitAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.parentAuthProvider = authenticationProvider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication currentAuthentication = this.parentAuthProvider.authenticate(authentication);

        if (this.updateCache(authentication)) {
            this.cache.put(currentAuthentication.getName(), Instant.now());
            return currentAuthentication;
        }
        throw new BadCredentialsException("Not allowed, too fast");
    }

    private boolean updateCache(Authentication currentAuthentication) {
        Instant prevLoginInstant = this.cache.get(currentAuthentication.getName());
        val now = Instant.now();
        return prevLoginInstant == null || prevLoginInstant.plus(1, ChronoUnit.MINUTES).isBefore(now);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return parentAuthProvider.supports(authentication);
    }
}
