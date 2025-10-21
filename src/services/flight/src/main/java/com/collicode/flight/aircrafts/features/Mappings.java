package com.collicode.flight.aircrafts.features;

import com.collicode.flight.aircrafts.dtos.AircraftDto;
import com.collicode.flight.aircrafts.features.createaircraft.CreateAircraftCommand;
import com.collicode.flight.aircrafts.features.createaircraft.CreateAircraftMongoCommand;
import com.collicode.flight.aircrafts.features.createaircraft.CreateAircraftRequestDto;
import com.collicode.flight.aircrafts.models.Aircraft;
import com.collicode.flight.data.jpa.entities.AircraftEntity;
import com.collicode.flight.data.mongo.documents.AircraftDocument;
import com.github.f4b6a3.uuid.UuidCreator;


public final class Mappings {

  public static AircraftEntity toAircraftEntity(Aircraft aircraft) {
    return new AircraftEntity(
      aircraft.getId().getAircraftId(),
      aircraft.getName(),
      aircraft.getModel(),
      aircraft.getManufacturingYear(),
      aircraft.getCreatedAt(),
      aircraft.getCreatedBy(),
      aircraft.getLastModified(),
      aircraft.getLastModifiedBy(),
      aircraft.getVersion(),
      aircraft.isDeleted()
    );
  }


  public static AircraftDto toAircraftDto(AircraftEntity aircraftEntity) {
    return new AircraftDto(
      aircraftEntity.getId(),
      aircraftEntity.getName().getName(),
      aircraftEntity.getModel().getModel(),
      aircraftEntity.getManufacturingYear().getManufacturingYear());
  }

  public static CreateAircraftCommand toCreateAircraftCommand(CreateAircraftRequestDto createAircraftRequestDto) {
    return new CreateAircraftCommand(
      UuidCreator.getTimeOrderedEpoch(),
      createAircraftRequestDto.name(),
      createAircraftRequestDto.model(),
      createAircraftRequestDto.manufacturingYear()
    );
  }

  public static AircraftDocument toAircraftDocument(CreateAircraftMongoCommand createAircraftMongoCommand) {
    return new AircraftDocument(
      createAircraftMongoCommand.id(),
      createAircraftMongoCommand.name(),
      createAircraftMongoCommand.model(),
      createAircraftMongoCommand.manufacturingYear(),
      createAircraftMongoCommand.isDeleted()
    );
  }

  public static AircraftDocument toAircraftDocument(AircraftEntity aircraftEntity) {
    return new AircraftDocument(
      aircraftEntity.getId(),
      aircraftEntity.getName().getName(),
      aircraftEntity.getModel().getModel(),
      aircraftEntity.getManufacturingYear().getManufacturingYear(),
      aircraftEntity.isDeleted()
    );
  }
}
