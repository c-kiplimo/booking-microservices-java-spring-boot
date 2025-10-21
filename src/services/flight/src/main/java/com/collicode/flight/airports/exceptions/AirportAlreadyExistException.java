package com.collicode.flight.airports.exceptions;


import com.collicode.buildingblocks.core.exception.ConflictException;

public class AirportAlreadyExistException extends ConflictException {
  public AirportAlreadyExistException() {
    super("Airport already exists!");
  }
}
