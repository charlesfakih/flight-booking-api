package com.charlesfakih.flightbooking.service;

import com.charlesfakih.flightbooking.domain.Flight;
import com.charlesfakih.flightbooking.exception.FlightNotFoundException;
import com.charlesfakih.flightbooking.repository.FlightRepository;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight getFlightByNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(() -> new FlightNotFoundException(flightNumber));
    }
}
