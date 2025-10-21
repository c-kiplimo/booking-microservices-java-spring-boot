package com.collicode.flight.seats.features.createseat;



import com.collicode.buildingblocks.core.event.DomainEvent;
import com.collicode.flight.seats.enums.SeatClass;
import com.collicode.flight.seats.enums.SeatType;

import java.util.UUID;


public record SeatCreatedDomainEvent(
  UUID id,
  String seatNumber,
  SeatType seatType,
  SeatClass seatClass,
  UUID flightId,
  boolean isDeleted) implements DomainEvent {
}

