package com.collicode.flight.flights.features.getflightbyid;


import com.collicode.buildingblocks.mediator.abstractions.queries.IQueryHandler;
import com.collicode.flight.data.mongo.documents.FlightDocument;
import com.collicode.flight.data.mongo.repositories.FlightReadRepository;
import com.collicode.flight.flights.dtos.FlightDto;
import com.collicode.flight.flights.exceptions.FlightNotFoundException;
import com.collicode.flight.flights.features.Mappings;
import org.springframework.stereotype.Service;

@Service
public class GetFlightByIdQueryHandler implements IQueryHandler<GetFlightByIdQuery, FlightDto> {
  private final FlightReadRepository flightReadRepository;

  public GetFlightByIdQueryHandler(FlightReadRepository flightReadRepository) {
    this.flightReadRepository = flightReadRepository;
  }

  @Override
  public FlightDto handle(GetFlightByIdQuery query) {
    FlightDocument flightDocument = flightReadRepository.findByFlightIdAndIsDeletedFalse(query.id());

    if (flightDocument == null) {
      throw new FlightNotFoundException();
    }

    return Mappings.toFlightDto(flightDocument);
  }
}
