package com.collicode.flight.airports.exceptions;


import com.collicode.buildingblocks.core.exception.BadRequestException;

import java.util.UUID;

public class InvalidAirportIdException extends BadRequestException {
    public InvalidAirportIdException(UUID airportId) {
        super("Airport ID: '" + airportId.toString() + "' is invalid.");
    }
}

