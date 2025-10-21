package com.collicode.booking.bookings.exceptions;


import com.collicode.buildingblocks.core.exception.NotFoundException;

public class SeatNumberIsNotAvailableException extends NotFoundException {
    public SeatNumberIsNotAvailableException() {
        super("SeatNumber is not available!");
    }
}

