package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface FlightRepository extends CrudRepository<Flight, Integer> {
    public Iterable<Flight> getAllByPilot(String pilotName);
}
