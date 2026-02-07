package com.collicode.passenger.passengers.features.deletepassenger;


import com.collicode.buildingblocks.mediator.abstractions.commands.ICommandHandler;
import com.collicode.buildingblocks.mediator.abstractions.requests.Unit;
import com.collicode.passenger.data.mongo.documents.PassengerDocument;
import com.collicode.passenger.data.mongo.repositories.PassengerReadRepository;
import com.collicode.passenger.passengers.exceptions.PassengerNotFoundException;
import com.collicode.passenger.passengers.features.Mappings;
import org.springframework.stereotype.Service;


@Service
public class DeletePassengerMongoCommandHandler implements ICommandHandler<DeletePassengerMongoCommand, Unit> {

    private final PassengerReadRepository passengerReadRepository;

    public DeletePassengerMongoCommandHandler(PassengerReadRepository passengerReadRepository) {
        this.passengerReadRepository = passengerReadRepository;
    }

    public Unit handle(DeletePassengerMongoCommand command) {

        PassengerDocument passengerDocument = Mappings.toPassengerDocument(command);

        var passenger = passengerReadRepository.findPassengerByPassengerIdAndIsDeletedFalse(passengerDocument.getPassengerId());

        if (passenger == null) {
            throw new PassengerNotFoundException();
        }

        passengerReadRepository.save(passengerDocument);

        return Unit.VALUE;
    }
}