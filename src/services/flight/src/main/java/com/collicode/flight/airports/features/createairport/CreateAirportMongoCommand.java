package com.collicode.flight.airports.features.createairport;



import com.collicode.buildingblocks.core.event.InternalCommand;
import com.collicode.buildingblocks.mediator.abstractions.commands.ICommand;
import com.collicode.buildingblocks.mediator.abstractions.requests.Unit;

import java.util.UUID;

public record CreateAirportMongoCommand(
  UUID id,
  String name,
  String code,
  String address,
  boolean isDeleted) implements ICommand<Unit>, InternalCommand {
}
