package com.collicode.flight.seats.features.reserveseat;

import java.util.UUID;

public record ReserveSeatRequestDto(
  String seatNumber,
  UUID flightId){
}

