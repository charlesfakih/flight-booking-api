package com.charlesfakih.flightbooking.domain;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Flight {

    private final String flightNumber;
    private final String origin;
    private final String destination;
    private final LocalDateTime departureTime;
    private final int totalSeats;
    // AtomicInteger ensures seat countdown is thread-safe without explicit locking
    private final AtomicInteger availableSeats;

    public Flight(String flightNumber, String origin, String destination,
                  LocalDateTime departureTime, int totalSeats) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.totalSeats = totalSeats;
        this.availableSeats = new AtomicInteger(totalSeats);
    }

    /**
     * Atomically attempts to reserve one seat.
     * Returns true if successful, false if the flight is fully booked.
     * Using compareAndSet in a loop prevents TOCTOU race conditions —
     * two concurrent requests cannot both pass the availability check.
     */
    public boolean reserveSeat() {
        while (true) {
            int current = availableSeats.get();
            if (current <= 0) return false;
            if (availableSeats.compareAndSet(current, current - 1)) return true;
        }
    }

    public String getFlightNumber() { return flightNumber; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public int getTotalSeats() { return totalSeats; }
    public int getAvailableSeats() { return availableSeats.get(); }
}
