package com.collicode.booking.bookings.exceptions;


import com.collicode.buildingblocks.core.exception.NotFoundException;

public class PassengerNotFoundException extends NotFoundException {
    public PassengerNotFoundException() {
        super("Passenger not found!");
    }
}
