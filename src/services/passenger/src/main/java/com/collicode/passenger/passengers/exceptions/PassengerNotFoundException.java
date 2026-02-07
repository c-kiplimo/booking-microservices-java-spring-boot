package com.collicode.passenger.passengers.exceptions;


import com.collicode.buildingblocks.core.exception.NotFoundException;

public class PassengerNotFoundException extends NotFoundException {
    public PassengerNotFoundException() {
        super("Passenger not found!");
    }
}