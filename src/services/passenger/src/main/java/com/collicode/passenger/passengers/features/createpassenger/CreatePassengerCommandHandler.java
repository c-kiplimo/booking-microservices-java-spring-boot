package com.collicode.passenger.passengers.features.createpassenger;


import com.collicode.buildingblocks.mediator.abstractions.commands.ICommandHandler;
import com.collicode.passenger.data.jpa.entities.PassengerEntity;
import com.collicode.passenger.data.jpa.repositories.PassengerRepository;
import com.collicode.passenger.passengers.dtos.PassengerDto;
import com.collicode.passenger.passengers.exceptions.PassengerAlreadyExistException;
import com.collicode.passenger.passengers.features.Mappings;
import com.collicode.passenger.passengers.models.Passenger;
import com.collicode.passenger.passengers.valueobjects.Age;
import com.collicode.passenger.passengers.valueobjects.Name;
import com.collicode.passenger.passengers.valueobjects.PassengerId;
import com.collicode.passenger.passengers.valueobjects.PassportNumber;
import org.springframework.stereotype.Service;

@Service
public class CreatePassengerCommandHandler implements ICommandHandler<CreatePassengerCommand, PassengerDto> {
    private final PassengerRepository passengerRepository;

    public CreatePassengerCommandHandler(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public PassengerDto handle(CreatePassengerCommand command) {

        PassengerEntity existPassenger = passengerRepository.findPassengerByPassportNumberAndIsDeletedFalse(command.passportNumber());
        if (existPassenger != null) {
            throw new PassengerAlreadyExistException();
        }

        Passenger passengerAggregate = Passenger.create(
                new PassengerId(command.id()),
                new Name(command.name()),
                new PassportNumber(command.passportNumber()),
                command.passengerType(),
                new Age(command.age())
        );

        PassengerEntity passengerEntity = Mappings.toPassengerEntity(passengerAggregate);

        PassengerEntity createdPassenger = passengerRepository.save(passengerEntity);

        return Mappings.toPassengerDto(createdPassenger);
    }
}
