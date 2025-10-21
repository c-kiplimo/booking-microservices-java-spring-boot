package com.collicode.booking.bookings.features.createbooking;


import java.util.UUID;

public record CreateBookingRequestDto(
        UUID passengerId,
        UUID flightId,
        String description
) {
}


