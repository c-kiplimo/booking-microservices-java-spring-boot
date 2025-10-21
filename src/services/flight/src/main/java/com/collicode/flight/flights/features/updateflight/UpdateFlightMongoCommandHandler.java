package com.collicode.flight.flights.features.updateflight;


import com.collicode.buildingblocks.mediator.abstractions.commands.ICommandHandler;
import com.collicode.buildingblocks.mediator.abstractions.requests.Unit;
import com.collicode.flight.data.mongo.documents.FlightDocument;
import com.collicode.flight.data.mongo.repositories.FlightReadRepository;
import com.collicode.flight.flights.exceptions.FlightNotFoundException;
import com.collicode.flight.flights.features.Mappings;
import org.springframework.stereotype.Service;


@Service
public class UpdateFlightMongoCommandHandler implements ICommandHandler<UpdateFlightMongoCommand, Unit> {

  private final FlightReadRepository flightReadRepository;

  public UpdateFlightMongoCommandHandler(FlightReadRepository flightReadRepository) {
    this.flightReadRepository = flightReadRepository;
  }

  public Unit handle(UpdateFlightMongoCommand command) {

    FlightDocument flight = flightReadRepository.findByFlightIdAndIsDeletedFalse(command.id());

    if (flight == null) {
      throw new FlightNotFoundException();
    }

    FlightDocument flightDocument = Mappings.toFlightDocument(flight.getId(), command);

    flightReadRepository.save(flightDocument);

    return Unit.VALUE;
  }
}

