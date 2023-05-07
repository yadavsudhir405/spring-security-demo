package com.example.demo;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import static java.lang.String.format;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, AuthenticationEventPublisher publisher) throws Exception {
        {
            httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).authenticationEventPublisher(publisher);
        }

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(
                        configurer -> {
                            configurer.antMatchers("/favicon.ico").permitAll();
                            configurer.antMatchers("/public").permitAll();
                            configurer.anyRequest().authenticated();
                        }
                )
                .authenticationProvider(new SuperUserAuthenticationProvider())
                .apply(new RobotLoginConfigurer())
                    .passwords("beep-beep")
                .and()
                .formLogin(withDefaults())
                .oauth2Login(withDefaults())
                .build();
    }

    @Bean
    public UserDetailsService userDetails(UserService userService) {
        return userService::findByFirstName;
    }

    @Bean
    public ApplicationListener<AuthenticationSuccessEvent> authenticationSuccessEventApplicationListener() {
        return event -> {
            System.out.println(format("🙏 Authentication success 🎉  [%s] [%s]",
                    event.getAuthentication().getClass().getName(),
                    event.getAuthentication().getName()
            ));
        };

    }


}
