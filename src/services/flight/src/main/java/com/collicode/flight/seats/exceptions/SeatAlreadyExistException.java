package com.collicode.flight.seats.exceptions;


import com.collicode.buildingblocks.core.exception.ConflictException;

public class SeatAlreadyExistException extends ConflictException {
    public SeatAlreadyExistException() {
        super("Seat already exists!");
    }
}

