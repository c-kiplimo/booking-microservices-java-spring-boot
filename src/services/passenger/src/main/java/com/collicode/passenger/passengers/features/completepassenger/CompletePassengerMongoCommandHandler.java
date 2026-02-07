package com.collicode.passenger.passengers.features.completepassenger;


import com.collicode.buildingblocks.mediator.abstractions.commands.ICommandHandler;
import com.collicode.buildingblocks.mediator.abstractions.requests.Unit;
import com.collicode.passenger.data.mongo.documents.PassengerDocument;
import com.collicode.passenger.data.mongo.repositories.PassengerReadRepository;
import com.collicode.passenger.passengers.exceptions.PassengerNotFoundException;
import com.collicode.passenger.passengers.features.Mappings;
import org.springframework.stereotype.Service;


@Service
public class CompletePassengerMongoCommandHandler implements ICommandHandler<CompletePassengerMongoCommand, Unit> {

    private final PassengerReadRepository passengerReadRepository;

    public CompletePassengerMongoCommandHandler(PassengerReadRepository passengerReadRepository) {
        this.passengerReadRepository = passengerReadRepository;
    }

    public Unit handle(CompletePassengerMongoCommand command) {

        PassengerDocument passenger = passengerReadRepository.findPassengerByPassengerIdAndIsDeletedFalse(command.id());

        if (passenger == null) {
            throw new PassengerNotFoundException();
        }

        PassengerDocument passengerDocument = Mappings.toPassengerDocument(passenger.getId(), command);

        passengerReadRepository.save(passengerDocument);

        return Unit.VALUE;
    }
}