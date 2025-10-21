package com.collicode.flight.seats.features.createseat;



import com.collicode.buildingblocks.core.event.InternalCommand;
import com.collicode.buildingblocks.mediator.abstractions.commands.ICommand;
import com.collicode.flight.seats.dtos.SeatDto;
import com.collicode.flight.seats.enums.SeatClass;
import com.collicode.flight.seats.enums.SeatType;

import java.util.UUID;

public record CreateSeatCommand(
  UUID id,
  String seatNumber,
  SeatType seatType,
  SeatClass seatClass,
  UUID flightId
) implements ICommand<SeatDto>, InternalCommand {
}


