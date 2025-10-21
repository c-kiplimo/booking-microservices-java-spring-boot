package com.collicode.flight.aircrafts.features.createaircraft;


import com.collicode.buildingblocks.mediator.abstractions.commands.ICommandHandler;
import com.collicode.buildingblocks.mediator.abstractions.requests.Unit;
import com.collicode.flight.aircrafts.exceptions.AircraftAlreadyExistException;
import com.collicode.flight.aircrafts.features.Mappings;
import com.collicode.flight.data.mongo.documents.AircraftDocument;
import com.collicode.flight.data.mongo.repositories.AircraftReadRepository;
import org.springframework.stereotype.Service;


@Service
public class CreateAircraftMongoCommandHandler implements ICommandHandler<CreateAircraftMongoCommand, Unit> {

  private final AircraftReadRepository aircraftReadRepository;

  public CreateAircraftMongoCommandHandler(AircraftReadRepository aircraftReadRepository) {
    this.aircraftReadRepository = aircraftReadRepository;
  }

  public Unit handle(CreateAircraftMongoCommand command) {

    AircraftDocument aircraftDocument = Mappings.toAircraftDocument(command);

    var aircraftExist = aircraftReadRepository.findByAircraftIdAndIsDeletedFalse(aircraftDocument.getAircraftId());

    if (aircraftExist != null) {
      throw new AircraftAlreadyExistException();
    }

    aircraftReadRepository.save(aircraftDocument);

    return Unit.VALUE;
  }
}

