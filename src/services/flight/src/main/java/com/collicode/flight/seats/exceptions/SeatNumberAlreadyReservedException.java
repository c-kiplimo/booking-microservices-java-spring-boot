package com.collicode.flight.seats.exceptions;


import com.collicode.buildingblocks.core.exception.BadRequestException;

public class SeatNumberAlreadyReservedException extends BadRequestException {
    public SeatNumberAlreadyReservedException() {
        super("Seat number already reserved!");
    }
}

