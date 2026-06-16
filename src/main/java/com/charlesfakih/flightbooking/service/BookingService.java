package com.charlesfakih.flightbooking.service;

import com.charlesfakih.flightbooking.domain.Booking;
import com.charlesfakih.flightbooking.domain.Flight;
import com.charlesfakih.flightbooking.domain.Passenger;
import com.charlesfakih.flightbooking.exception.FlightFullException;
import com.charlesfakih.flightbooking.exception.FlightNotFoundException;
import com.charlesfakih.flightbooking.repository.BookingRepository;
import com.charlesfakih.flightbooking.repository.FlightRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;

    public BookingService(FlightRepository flightRepository,
                          BookingRepository bookingRepository) {
        this.flightRepository = flightRepository;
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(String flightNumber, Passenger passenger) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(() -> new FlightNotFoundException(flightNumber));

        // reserveSeat() atomically checks and decrements using CAS loop —
        // guarantees no two threads can both succeed when only one seat remains
        boolean reserved = flight.reserveSeat();
        if (!reserved) {
            throw new FlightFullException(flightNumber);
        }

        Booking booking = new Booking(flightNumber, passenger);
        return bookingRepository.save(booking);
    }
}
