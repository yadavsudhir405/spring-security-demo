package com.example.demo;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users findByFirstName(String name) {
        return this.userRepository.findByFirstName(name).orElseThrow(() -> new UsernameNotFoundException(name+ "not fount"));
    }
}
