package com.collicode.booking.bookings.features.createbooking;


import com.collicode.booking.bookings.valueobjects.PassengerInfo;
import com.collicode.booking.bookings.valueobjects.Trip;
import com.collicode.buildingblocks.core.event.InternalCommand;
import com.collicode.buildingblocks.mediator.abstractions.commands.ICommand;
import com.collicode.buildingblocks.mediator.abstractions.requests.Unit;

import java.util.UUID;


public record CreateBookingMongoCommand(
        UUID id,
        PassengerInfo passengerInfo,
        Trip trip,
        boolean isDeleted) implements ICommand<Unit>, InternalCommand {
}

