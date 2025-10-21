package com.collicode.flight.seats.features.createseat;



import com.collicode.flight.seats.enums.SeatClass;
import com.collicode.flight.seats.enums.SeatType;

import java.util.UUID;

public record CreateSeatRequestDto(
  String seatNumber,
  SeatType seatType,
  SeatClass seatClass,
  UUID flightId){
}

