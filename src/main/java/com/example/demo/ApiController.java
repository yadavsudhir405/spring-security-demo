package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class ApiController {

    @GetMapping("/public")
    public String publicGreeting(Users users) {
        return "Public Greeting for "+users.getFirstName() +" and email "+ users.getEmail() +"  😄";
    }
    @GetMapping("/private")
    public String privateGreeting(Users users) {
        return "Private Greeting for "+users.getEmail()+"and email "+users.getEmail()+" 😎";
    }




}
