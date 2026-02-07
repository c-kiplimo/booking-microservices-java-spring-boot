package com.collicode.passenger.passengers.features.completepassenger;


import com.collicode.buildingblocks.core.event.InternalCommand;
import com.collicode.buildingblocks.mediator.abstractions.commands.ICommand;
import com.collicode.buildingblocks.mediator.abstractions.requests.Unit;
import com.collicode.passenger.passengers.enums.PassengerType;

import java.util.UUID;


public record CompletePassengerMongoCommand(
        UUID id,
        String name,
        String passportNumber,
        PassengerType passengerType,
        int age,
        boolean isCompleted) implements ICommand<Unit>, InternalCommand {
}