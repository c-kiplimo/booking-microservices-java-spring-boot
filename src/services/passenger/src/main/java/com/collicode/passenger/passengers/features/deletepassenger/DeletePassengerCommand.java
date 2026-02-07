package com.collicode.passenger.passengers.features.deletepassenger;


import com.collicode.buildingblocks.core.event.InternalCommand;
import com.collicode.buildingblocks.mediator.abstractions.commands.ICommand;
import com.collicode.passenger.passengers.dtos.PassengerDto;

import java.util.UUID;

public record DeletePassengerCommand(
        UUID id
) implements ICommand<PassengerDto>, InternalCommand {
}