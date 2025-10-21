package com.collicode.flight.airports.features.createairport;


import com.collicode.buildingblocks.mediator.abstractions.commands.ICommandHandler;
import com.collicode.buildingblocks.mediator.abstractions.requests.Unit;
import com.collicode.flight.airports.exceptions.AirportAlreadyExistException;
import com.collicode.flight.airports.features.Mappings;
import com.collicode.flight.data.mongo.documents.AirportDocument;
import com.collicode.flight.data.mongo.repositories.AirportReadRepository;
import org.springframework.stereotype.Service;


@Service
public class CreateAirportMongoCommandHandler implements ICommandHandler<CreateAirportMongoCommand, Unit> {

  private final AirportReadRepository airportReadRepository;

  public CreateAirportMongoCommandHandler(AirportReadRepository airportReadRepository) {
    this.airportReadRepository = airportReadRepository;
  }

  public Unit handle(CreateAirportMongoCommand command) {

    AirportDocument airportDocument = Mappings.toAirportDocument(command);

    var airportExist = airportReadRepository.findByAirportIdAndIsDeletedFalse(airportDocument.getAirportId());

    if (airportExist != null) {
      throw new AirportAlreadyExistException();
    }

    airportReadRepository.save(airportDocument);

    return Unit.VALUE;
  }
}

