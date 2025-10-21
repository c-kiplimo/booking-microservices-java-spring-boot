package com.collicode.flight.flights.features.getavailableflights;


import com.collicode.buildingblocks.mediator.abstractions.queries.IQueryHandler;
import com.collicode.flight.data.mongo.documents.FlightDocument;
import com.collicode.flight.data.mongo.repositories.FlightReadRepository;
import com.collicode.flight.flights.dtos.FlightDto;
import com.collicode.flight.flights.features.Mappings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAvailableFlightsQueryHandler implements IQueryHandler<GetAvailableFlightsQuery, List<FlightDto>> {
  private final FlightReadRepository flightReadRepository;

  public GetAvailableFlightsQueryHandler(FlightReadRepository flightReadRepository) {
    this.flightReadRepository = flightReadRepository;
  }

  @Override
  public List<FlightDto> handle(GetAvailableFlightsQuery query) {
    List<FlightDocument> flightDocuments =  flightReadRepository.findAllByIsDeletedFalse();
    return flightDocuments.stream().map(Mappings::toFlightDto).toList();
  }
}
