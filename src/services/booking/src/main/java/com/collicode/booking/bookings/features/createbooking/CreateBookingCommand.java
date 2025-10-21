package com.collicode.booking.bookings.features.createbooking;


import com.collicode.booking.bookings.dtos.BookingDto;
import com.collicode.buildingblocks.core.event.InternalCommand;
import com.collicode.buildingblocks.mediator.abstractions.commands.ICommand;

import java.util.UUID;

public record CreateBookingCommand(
        UUID id,
        UUID passengerId,
        UUID flightId,
        String description
) implements ICommand<BookingDto>, InternalCommand {
}