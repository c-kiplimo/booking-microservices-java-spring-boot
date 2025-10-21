package com.collicode.flight.flights.exceptions;


import com.collicode.buildingblocks.core.exception.NotFoundException;

public class FlightNotFoundException extends NotFoundException {
    public FlightNotFoundException() {
        super("Flight not found!");
    }
}
