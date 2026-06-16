package com.charlesfakih.flightbooking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateBookingRequest(
        @NotBlank(message = "Passenger name is required")
        String passengerName,

        @NotBlank(message = "Passenger email is required")
        @Email(message = "Passenger email must be valid")
        String passengerEmail
) {}
