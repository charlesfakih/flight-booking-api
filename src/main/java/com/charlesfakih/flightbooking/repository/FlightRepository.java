package com.charlesfakih.flightbooking.repository;

import com.charlesfakih.flightbooking.domain.Flight;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class FlightRepository {

    // ConcurrentHashMap for thread-safe access without locking the whole map
    private final ConcurrentHashMap<String, Flight> store = new ConcurrentHashMap<>();

    public Flight save(Flight flight) {
        store.put(flight.getFlightNumber(), flight);
        return flight;
    }

    public Optional<Flight> findByFlightNumber(String flightNumber) {
        return Optional.ofNullable(store.get(flightNumber));
    }

    public Collection<Flight> findAll() {
        return store.values();
    }
}
