package com.collicode.flight.flights.features.createflight;


import com.collicode.buildingblocks.mediator.abstractions.commands.ICommandHandler;
import com.collicode.flight.aircrafts.valueobjects.AircraftId;
import com.collicode.flight.airports.valueobjects.AirportId;
import com.collicode.flight.data.jpa.entities.FlightEntity;
import com.collicode.flight.data.jpa.repositories.FlightRepository;
import com.collicode.flight.flights.dtos.FlightDto;
import com.collicode.flight.flights.exceptions.FlightAlreadyExistException;
import com.collicode.flight.flights.features.Mappings;
import com.collicode.flight.flights.models.Flight;
import com.collicode.flight.flights.valueobjects.*;
import org.springframework.stereotype.Service;

@Service
public class CreateFlightCommandHandler implements ICommandHandler<CreateFlightCommand, FlightDto> {
  private final FlightRepository flightRepository;

  public CreateFlightCommandHandler(
    FlightRepository flightRepository) {
    this.flightRepository = flightRepository;
  }

  @Override
  public FlightDto handle(CreateFlightCommand command) {

    FlightEntity existFlight = flightRepository.findFlightByIdAndIsDeletedFalse(command.id());
    if (existFlight!= null) {
      throw new FlightAlreadyExistException();
    }

    Flight flight = Flight.create(
      new FlightId(command.id()),
      new FlightNumber(command.flightNumber()),
      new AircraftId(command.aircraftId()),
      new AirportId(command.departureAirportId()),
      new DepartureDate(command.departureDate()),
      new ArriveDate(command.arriveDate()),
      new AirportId(command.arriveAirportId()),
      new DurationMinutes(command.durationMinutes()),
      new FlightDate(command.flightDate()),
      command.status(),
      new Price(command.price())
    );

    FlightEntity flightEntity = Mappings.toFlightEntity(flight);

    FlightEntity flightCreated = flightRepository.save(flightEntity);
    return Mappings.toFlightDto(flightCreated);
  }
}
