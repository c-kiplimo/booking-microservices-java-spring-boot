package com.collicode.booking.bookings.features.createbooking;


import com.collicode.booking.bookings.valueobjects.PassengerInfo;
import com.collicode.booking.bookings.valueobjects.Trip;
import com.collicode.buildingblocks.core.event.DomainEvent;

import java.util.UUID;


public record BookingCreatedDomainEvent(
        UUID id,
        PassengerInfo passengerInfo,
        Trip trip,
        boolean isDeleted) implements DomainEvent {
}
