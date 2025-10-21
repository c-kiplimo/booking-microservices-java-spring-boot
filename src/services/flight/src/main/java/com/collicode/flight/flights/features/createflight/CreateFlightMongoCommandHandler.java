package com.collicode.flight.flights.features.createflight;


import com.collicode.buildingblocks.mediator.abstractions.commands.ICommandHandler;
import com.collicode.buildingblocks.mediator.abstractions.requests.Unit;
import com.collicode.flight.data.mongo.documents.FlightDocument;
import com.collicode.flight.data.mongo.repositories.FlightReadRepository;
import com.collicode.flight.flights.exceptions.FlightAlreadyExistException;
import com.collicode.flight.flights.features.Mappings;
import org.springframework.stereotype.Service;


@Service
public class CreateFlightMongoCommandHandler implements ICommandHandler<CreateFlightMongoCommand, Unit> {
  private final FlightReadRepository flightReadRepository;

  public CreateFlightMongoCommandHandler(FlightReadRepository flightReadRepository) {
    this.flightReadRepository = flightReadRepository;
  }

  public Unit handle(CreateFlightMongoCommand command) {

      FlightDocument flightDocument = Mappings.toFlightDocument(command);

      var flightExist = flightReadRepository.findByFlightIdAndIsDeletedFalse(flightDocument.getFlightId());

      if (flightExist != null) {
        throw new FlightAlreadyExistException();
      }

      flightReadRepository.save(flightDocument);

      return Unit.VALUE;
  }
}
