package com.collicode.passenger.passengers.features.createpassenger;


import com.collicode.buildingblocks.core.event.InternalCommand;
import com.collicode.buildingblocks.mediator.abstractions.commands.ICommand;
import com.collicode.passenger.passengers.dtos.PassengerDto;
import com.collicode.passenger.passengers.enums.PassengerType;

import java.util.UUID;

public record CreatePassengerCommand(
        UUID id,
        String name,
        String passportNumber,
        PassengerType passengerType,
        int age
) implements ICommand<PassengerDto>, InternalCommand {
    public CreatePassengerCommand {
    }
}

