package com.example.demo;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightsController {

    private final FlightService flightService;

    public FlightsController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return this.flightService.findAll();
    }

    public Iterable<Flight> getAllFlightsOfUser(SecurityProperties.User user) {
        return this.flightService.findAllByUser(user);
    }
}
