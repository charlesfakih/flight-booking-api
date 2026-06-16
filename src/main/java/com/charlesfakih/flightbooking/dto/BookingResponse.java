package com.charlesfakih.flightbooking.dto;

import com.charlesfakih.flightbooking.domain.Booking;

import java.time.LocalDateTime;

public record BookingResponse(
        String bookingReference,
        String flightNumber,
        String passengerName,
        String passengerEmail,
        LocalDateTime bookedAt
) {
    public static BookingResponse from(Booking booking) {
        return new BookingResponse(
                booking.getBookingReference(),
                booking.getFlightNumber(),
                booking.getPassenger().getName(),
                booking.getPassenger().getEmail(),
                booking.getBookedAt()
        );
    }
}
