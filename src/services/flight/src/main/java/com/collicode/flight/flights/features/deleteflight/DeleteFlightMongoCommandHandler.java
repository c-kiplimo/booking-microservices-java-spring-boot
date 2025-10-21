package com.collicode.flight.flights.features.deleteflight;


import com.collicode.buildingblocks.mediator.abstractions.commands.ICommandHandler;
import com.collicode.buildingblocks.mediator.abstractions.requests.Unit;
import com.collicode.flight.data.mongo.documents.FlightDocument;
import com.collicode.flight.data.mongo.repositories.FlightReadRepository;
import com.collicode.flight.flights.exceptions.FlightNotFoundException;
import com.collicode.flight.flights.features.Mappings;
import org.springframework.stereotype.Service;


@Service
public class DeleteFlightMongoCommandHandler implements ICommandHandler<DeleteFlightMongoCommand, Unit> {


    private final FlightReadRepository flightReadRepository;

    public DeleteFlightMongoCommandHandler(FlightReadRepository flightReadRepository) {
        this.flightReadRepository = flightReadRepository;
    }

    public Unit handle(DeleteFlightMongoCommand command) {

        FlightDocument flightDocument = Mappings.toFlightDocument(command);

        var flight = flightReadRepository.findByFlightIdAndIsDeletedFalse(flightDocument.getFlightId());

        if (flight == null) {
            throw new FlightNotFoundException();
        }

        flightReadRepository.save(flightDocument);

        return Unit.VALUE;
    }
}

