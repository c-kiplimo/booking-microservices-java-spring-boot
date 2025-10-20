package com.collicode.passenger.passengers.features.createpassenger;


import com.collicode.buildingblocks.mediator.abstractions.commands.ICommandHandler;
import com.collicode.buildingblocks.mediator.abstractions.requests.Unit;
import com.collicode.passenger.data.mongo.documents.PassengerDocument;
import com.collicode.passenger.data.mongo.repositories.PassengerReadRepository;
import com.collicode.passenger.passengers.exceptions.PassengerAlreadyExistException;
import com.collicode.passenger.passengers.features.Mappings;
import org.springframework.stereotype.Service;

@Service
public class CreatePassengerMongoCommandHandler implements ICommandHandler<CreatePassengerMongoCommand, Unit> {

    private final PassengerReadRepository passengerReadRepository;

    public CreatePassengerMongoCommandHandler(PassengerReadRepository passengerReadRepository) {
        this.passengerReadRepository = passengerReadRepository;
    }

    public Unit handle(CreatePassengerMongoCommand command) {

        PassengerDocument passengerDocument = Mappings.toPassengerDocument(command);

        var passengerExist = passengerReadRepository.findPassengerByPassengerIdAndIsDeletedFalse(passengerDocument.getPassengerId());

        if (passengerExist != null) {
            throw new PassengerAlreadyExistException();
        }

        passengerReadRepository.save(passengerDocument);

        return Unit.VALUE;
    }
}

