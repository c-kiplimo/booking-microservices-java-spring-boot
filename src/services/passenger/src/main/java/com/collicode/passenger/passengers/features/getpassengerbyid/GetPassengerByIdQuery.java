package com.collicode.passenger.passengers.features.getpassengerbyid;


import com.collicode.buildingblocks.mediator.abstractions.queries.IQuery;
import com.collicode.passenger.passengers.dtos.PassengerDto;

import java.util.UUID;

public record GetPassengerByIdQuery(
        UUID id
) implements IQuery<PassengerDto> {
}

