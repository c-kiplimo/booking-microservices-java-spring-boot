package com.collicode.flight.airports.features.createairport;


import com.collicode.buildingblocks.mediator.abstractions.commands.ICommandHandler;
import com.collicode.flight.airports.dtos.AirportDto;
import com.collicode.flight.airports.exceptions.AirportAlreadyExistException;
import com.collicode.flight.airports.features.Mappings;
import com.collicode.flight.airports.models.Airport;
import com.collicode.flight.airports.valueobjects.Address;
import com.collicode.flight.airports.valueobjects.AirportId;
import com.collicode.flight.airports.valueobjects.Code;
import com.collicode.flight.airports.valueobjects.Name;
import com.collicode.flight.data.jpa.entities.AirportEntity;
import com.collicode.flight.data.jpa.repositories.AirportRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateAirportCommandHandler implements ICommandHandler<CreateAirportCommand, AirportDto> {
  private final AirportRepository airportRepository;

  public CreateAirportCommandHandler(
    AirportRepository airportRepository) {
    this.airportRepository = airportRepository;
  }

  @Override
  public AirportDto handle(CreateAirportCommand command) {

    AirportEntity existAirport = airportRepository.findAirportByCodeAndIsDeletedFalse(command.code());
    if (existAirport != null) {
      throw new AirportAlreadyExistException();
    }

    Airport airport = Airport.create(
      new AirportId(command.id()),
      new Name(command.name()),
      new Code(command.code()),
      new Address(command.address())
    );

    AirportEntity airportEntity = Mappings.toAirportEntity(airport);

    AirportEntity airportCreated = airportRepository.save(airportEntity);
    return Mappings.toAirportDto(airportCreated);
  }
}
