package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RobotAuthenticationFilter extends OncePerRequestFilter {

    private final ProviderManager providerManager;

    public RobotAuthenticationFilter(ProviderManager providerManager) {
        this.providerManager = providerManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerValue = request.getHeader("robot");
        if (headerValue != null) {
            RobotAuthenticationToken authRequest = RobotAuthenticationToken.unAuthenticated(headerValue, "");
            try {
                Authentication sucessAuthentication = this.providerManager.authenticate(authRequest);
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(sucessAuthentication);
                SecurityContextHolder.setContext(securityContext);
                filterChain.doFilter(request, response);
                return;

            } catch (AuthenticationException e) {
                SecurityContextHolder.clearContext();
            }
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-type","text/plain;charset=utf-8");
            response.getWriter().write("You're not Mr. Robot 🤖⛔️");
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
