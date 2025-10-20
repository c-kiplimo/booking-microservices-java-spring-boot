package com.collicode.passenger.passengers.exceptions;


import com.collicode.buildingblocks.core.exception.ConflictException;

public class PassengerAlreadyExistException extends ConflictException {
    public PassengerAlreadyExistException() {
        super("Passenger already exists!");
    }
}

