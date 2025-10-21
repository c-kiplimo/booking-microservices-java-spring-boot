package com.collicode.flight.seats.features.getavailableseats;



import com.collicode.buildingblocks.mediator.abstractions.queries.IQuery;
import com.collicode.flight.seats.dtos.SeatDto;

import java.util.List;
import java.util.UUID;


public record GetAvailableSeatsQuery(
  UUID flightId
) implements IQuery<List<SeatDto>> {
}


