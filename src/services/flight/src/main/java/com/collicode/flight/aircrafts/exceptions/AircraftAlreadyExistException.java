package com.collicode.flight.aircrafts.exceptions;


import com.collicode.buildingblocks.core.exception.ConflictException;

public class AircraftAlreadyExistException extends ConflictException {
  public AircraftAlreadyExistException() {
    super("Aircraft already exists!");
  }
}

