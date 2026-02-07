package com.collicode.passenger.passengers.features.getallpassengers;


import com.collicode.buildingblocks.mediator.abstractions.queries.IQuery;
import com.collicode.passenger.passengers.dtos.PassengerDto;

import java.util.List;

public record GetAllPassengersQuery() implements IQuery<List<PassengerDto>> {
}