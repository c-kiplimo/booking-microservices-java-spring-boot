package com.collicode.flight.flights.features.getavailableflights;



import com.collicode.buildingblocks.mediator.abstractions.queries.IQuery;
import com.collicode.flight.flights.dtos.FlightDto;

import java.util.List;

public record GetAvailableFlightsQuery() implements IQuery<List<FlightDto>> {
}


