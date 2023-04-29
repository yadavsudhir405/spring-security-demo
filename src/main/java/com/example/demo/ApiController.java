package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class ApiController {

    @GetMapping("/public")
    public String publicGreeting() {
        return "Public Greeting 😄";
    }
    @GetMapping("/private")
    public String privateGreeting() {
        return "Private Greeting 😎";
    }




}
