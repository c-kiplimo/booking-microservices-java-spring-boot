package com.collicode.booking.bookings.exceptions;


import com.collicode.buildingblocks.core.exception.ConflictException;

public class BookingAlreadyExistException extends ConflictException {
    public BookingAlreadyExistException() {
        super("Booking already exists!");
    }
}

