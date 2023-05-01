package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class RobotAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String robotHeader = request.getHeader("robot");
        if(robotHeader!= null) {
            if("beep beep".equalsIgnoreCase(robotHeader)){
                Authentication robotAuthenticationToken = new RobotAuthenticationToken(List.of(() -> "ROBOT"));
                robotAuthenticationToken.setAuthenticated(true);
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(robotAuthenticationToken);
                SecurityContextHolder.setContext(securityContext);
                filterChain.doFilter(request, response);
                return;
            }else {
                return;
            }

        }else {
            filterChain.doFilter(request, response);
        }
    }
}
