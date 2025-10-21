package com.collicode.flight.aircrafts.features.createaircraft;



import com.collicode.buildingblocks.core.event.InternalCommand;
import com.collicode.buildingblocks.mediator.abstractions.commands.ICommand;
import com.collicode.buildingblocks.mediator.abstractions.requests.Unit;

import java.util.UUID;

public record CreateAircraftMongoCommand(
  UUID id,
  String name,
  String model,
  int manufacturingYear,
  boolean isDeleted) implements ICommand<Unit>, InternalCommand {
}

