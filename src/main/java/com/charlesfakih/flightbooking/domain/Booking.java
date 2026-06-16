package com.charlesfakih.flightbooking.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Booking {

    private final String bookingReference;
    private final String flightNumber;
    private final Passenger passenger;
    private final LocalDateTime bookedAt;

    public Booking(String flightNumber, Passenger passenger) {
        this.bookingReference = UUID.randomUUID().toString();
        this.flightNumber = flightNumber;
        this.passenger = passenger;
        this.bookedAt = LocalDateTime.now();
    }

    public String getBookingReference() { return bookingReference; }
    public String getFlightNumber() { return flightNumber; }
    public Passenger getPassenger() { return passenger; }
    public LocalDateTime getBookedAt() { return bookedAt; }
}
