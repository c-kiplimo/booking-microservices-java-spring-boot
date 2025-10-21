package com.collicode.flight.seats.features.createseat;



import com.collicode.buildingblocks.core.event.InternalCommand;
import com.collicode.buildingblocks.mediator.abstractions.commands.ICommand;
import com.collicode.buildingblocks.mediator.abstractions.requests.Unit;
import com.collicode.flight.seats.enums.SeatClass;
import com.collicode.flight.seats.enums.SeatType;

import java.util.UUID;

public record CreateSeatMongoCommand(
  UUID id,
  String seatNumber,
  SeatType seatType,
  SeatClass seatClass,
  UUID flightId,
  boolean isDeleted) implements ICommand<Unit>, InternalCommand {
}

