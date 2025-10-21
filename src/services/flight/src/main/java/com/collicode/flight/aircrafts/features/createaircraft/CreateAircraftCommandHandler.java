package com.collicode.flight.aircrafts.features.createaircraft;


import com.collicode.buildingblocks.mediator.abstractions.commands.ICommandHandler;
import com.collicode.flight.aircrafts.dtos.AircraftDto;
import com.collicode.flight.aircrafts.exceptions.AircraftAlreadyExistException;
import com.collicode.flight.aircrafts.features.Mappings;
import com.collicode.flight.aircrafts.models.Aircraft;
import com.collicode.flight.aircrafts.valueobjects.AircraftId;
import com.collicode.flight.aircrafts.valueobjects.ManufacturingYear;
import com.collicode.flight.aircrafts.valueobjects.Model;
import com.collicode.flight.aircrafts.valueobjects.Name;
import com.collicode.flight.data.jpa.entities.AircraftEntity;
import com.collicode.flight.data.jpa.repositories.AircraftRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateAircraftCommandHandler implements ICommandHandler<CreateAircraftCommand, AircraftDto> {

  private final AircraftRepository aircraftRepository;

  public CreateAircraftCommandHandler(AircraftRepository aircraftRepository) {
    this.aircraftRepository = aircraftRepository;
  }

  @Override
  public AircraftDto handle(CreateAircraftCommand command) {

    AircraftEntity existAircraft = aircraftRepository.findAircraftByModelAndIsDeletedFalse(command.model());
    if (existAircraft != null) {
      throw new AircraftAlreadyExistException();
    }

    Aircraft aircraft = Aircraft.create(
      new AircraftId(command.id()),
      new Name(command.name()),
      new Model(command.model()),
      new ManufacturingYear(command.manufacturingYear())
    );

    AircraftEntity aircraftEntity = Mappings.toAircraftEntity(aircraft);

    AircraftEntity aircraftCreated = aircraftRepository.save(aircraftEntity);
    return Mappings.toAircraftDto(aircraftCreated);
  }
}
