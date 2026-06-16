package com.charlesfakih.flightbooking.repository;

import com.charlesfakih.flightbooking.domain.Booking;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BookingRepository {

    private final ConcurrentHashMap<String, Booking> store = new ConcurrentHashMap<>();

    public Booking save(Booking booking) {
        store.put(booking.getBookingReference(), booking);
        return booking;
    }
}
