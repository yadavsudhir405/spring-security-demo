package com.example.demo;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> findAll() {
        return StreamSupport.stream(this.flightRepository.findAll().spliterator(), false).toList();
    }

    public Iterable<Flight> findAllByUser(SecurityProperties.User user) {
        return this.flightRepository.getAllByPilot(user.getName());
    }
}
