package com.collicode.flight.flights.features.updateflight;


import com.collicode.buildingblocks.mediator.abstractions.commands.ICommandHandler;
import com.collicode.flight.aircrafts.valueobjects.AircraftId;
import com.collicode.flight.airports.valueobjects.AirportId;
import com.collicode.flight.data.jpa.entities.FlightEntity;
import com.collicode.flight.data.jpa.repositories.FlightRepository;
import com.collicode.flight.flights.dtos.FlightDto;
import com.collicode.flight.flights.exceptions.FlightNotFoundException;
import com.collicode.flight.flights.features.Mappings;
import com.collicode.flight.flights.models.Flight;
import com.collicode.flight.flights.valueobjects.*;
import org.springframework.stereotype.Service;

@Service
public class UpdateFlightCommandHandler implements ICommandHandler<UpdateFlightCommand, FlightDto> {
    private final FlightRepository flightRepository;

    public UpdateFlightCommandHandler(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public FlightDto handle(UpdateFlightCommand command) {

        FlightEntity existingFlight = flightRepository.findFlightByIdAndIsDeletedFalse(command.id());
        if (existingFlight == null) {
            throw new FlightNotFoundException();
        }

        Flight flight = Mappings.toFlightAggregate(existingFlight);

        flight.update(new FlightId(existingFlight.getId()), new FlightNumber(command.flightNumber()), new AircraftId(command.aircraftId()), new AirportId(command.departureAirportId()), new DepartureDate(command.departureDate()),
                new ArriveDate(command.arriveDate()), new AirportId(command.arriveAirportId()), new DurationMinutes(command.durationMinutes()), new FlightDate(command.flightDate()),
                command.status(), new Price(command.price()), command.isDeleted());

        FlightEntity flightEntity = Mappings.toFlightEntity(flight);

        FlightEntity updatedFlight = flightRepository.save(flightEntity);
        return Mappings.toFlightDto(updatedFlight);
    }
}
