package com.collicode.flight.airports.features.createairport;



import com.collicode.buildingblocks.core.event.InternalCommand;
import com.collicode.buildingblocks.mediator.abstractions.commands.ICommand;
import com.collicode.flight.airports.dtos.AirportDto;

import java.util.UUID;

public record CreateAirportCommand(
  UUID id,
  String name,
  String code,
  String address
  ) implements ICommand<AirportDto>, InternalCommand {
}

