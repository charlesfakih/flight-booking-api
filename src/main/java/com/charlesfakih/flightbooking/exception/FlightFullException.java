package com.charlesfakih.flightbooking.exception;

public class FlightFullException extends RuntimeException {
    public FlightFullException(String flightNumber) {
        super("Flight is fully booked: " + flightNumber);
    }
}
