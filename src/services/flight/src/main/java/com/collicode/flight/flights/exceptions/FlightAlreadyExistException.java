package com.collicode.flight.flights.exceptions;


import com.collicode.buildingblocks.core.exception.ConflictException;

public class FlightAlreadyExistException extends ConflictException {
    public FlightAlreadyExistException() {
        super("Flight already exists!");
    }
}
