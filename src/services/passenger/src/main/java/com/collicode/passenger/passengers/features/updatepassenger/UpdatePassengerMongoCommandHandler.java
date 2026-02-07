package com.collicode.passenger.passengers.features.updatepassenger;


import com.collicode.buildingblocks.mediator.abstractions.commands.ICommandHandler;
import com.collicode.buildingblocks.mediator.abstractions.requests.Unit;
import com.collicode.passenger.data.mongo.documents.PassengerDocument;
import com.collicode.passenger.data.mongo.repositories.PassengerReadRepository;
import com.collicode.passenger.passengers.exceptions.PassengerNotFoundException;
import com.collicode.passenger.passengers.features.Mappings;
import org.springframework.stereotype.Service;


@Service
public class UpdatePassengerMongoCommandHandler implements ICommandHandler<UpdatePassengerMongoCommand, Unit> {

    private final PassengerReadRepository passengerReadRepository;

    public UpdatePassengerMongoCommandHandler(PassengerReadRepository passengerReadRepository) {
        this.passengerReadRepository = passengerReadRepository;
    }

    public Unit handle(UpdatePassengerMongoCommand command) {

        PassengerDocument passenger = passengerReadRepository.findPassengerByPassengerIdAndIsDeletedFalse(command.id());

        if (passenger == null) {
            throw new PassengerNotFoundException();
        }

        PassengerDocument passengerDocument = Mappings.toPassengerDocument(passenger.getId(), command);

        passengerReadRepository.save(passengerDocument);

        return Unit.VALUE;
    }
}