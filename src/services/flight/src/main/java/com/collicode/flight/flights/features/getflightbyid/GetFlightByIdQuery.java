package com.collicode.flight.flights.features.getflightbyid;



import com.collicode.buildingblocks.mediator.abstractions.queries.IQuery;
import com.collicode.flight.flights.dtos.FlightDto;

import java.util.UUID;

public record GetFlightByIdQuery(
  UUID id
) implements IQuery<FlightDto> {
}


