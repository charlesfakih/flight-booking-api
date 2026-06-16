package com.charlesfakih.flightbooking.controller;

import com.charlesfakih.flightbooking.domain.Flight;
import com.charlesfakih.flightbooking.dto.CreateFlightRequest;
import com.charlesfakih.flightbooking.dto.FlightResponse;
import com.charlesfakih.flightbooking.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<FlightResponse> createFlight(@Valid @RequestBody CreateFlightRequest request) {
        Flight flight = new Flight(
                request.flightNumber(),
                request.origin(),
                request.destination(),
                request.departureTime(),
                request.totalSeats()
        );
        Flight saved = flightService.createFlight(flight);
        return ResponseEntity.status(HttpStatus.CREATED).body(FlightResponse.from(saved));
    }
}
