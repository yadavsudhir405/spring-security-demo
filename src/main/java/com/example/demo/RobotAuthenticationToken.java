package com.example.demo;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class RobotAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private Object credentials;

    public RobotAuthenticationToken(Object principal, Object credential, Collection<GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credential;
    }

    public static RobotAuthenticationToken unAuthenticated(Object principal, Object credentials){
        return new RobotAuthenticationToken(principal, credentials, List.of());
    }

    public static RobotAuthenticationToken authenticated(Object principal, Object credentials, Collection<GrantedAuthority> authorities) {
        RobotAuthenticationToken robotAuthenticationToken = new RobotAuthenticationToken(principal, credentials, authorities );
        robotAuthenticationToken.setAuthenticated(true);
        return robotAuthenticationToken;
    }


    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
