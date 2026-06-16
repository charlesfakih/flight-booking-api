package com.charlesfakih.flightbooking.controller;

import com.charlesfakih.flightbooking.domain.Booking;
import com.charlesfakih.flightbooking.domain.Passenger;
import com.charlesfakih.flightbooking.dto.BookingResponse;
import com.charlesfakih.flightbooking.dto.CreateBookingRequest;
import com.charlesfakih.flightbooking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flights")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/{flightNumber}/bookings")
    public ResponseEntity<BookingResponse> createBooking(
            @PathVariable String flightNumber,
            @Valid @RequestBody CreateBookingRequest request) {

        Passenger passenger = new Passenger(request.passengerName(), request.passengerEmail());
        Booking booking = bookingService.createBooking(flightNumber, passenger);
        return ResponseEntity.status(HttpStatus.CREATED).body(BookingResponse.from(booking));
    }
}
