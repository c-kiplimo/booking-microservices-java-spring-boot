package com.collicode.passenger.passengers.features.updatepassenger;


import com.collicode.buildingblocks.mediator.abstractions.commands.ICommandHandler;
import com.collicode.passenger.data.jpa.entities.PassengerEntity;
import com.collicode.passenger.data.jpa.repositories.PassengerRepository;
import com.collicode.passenger.passengers.dtos.PassengerDto;
import com.collicode.passenger.passengers.exceptions.PassengerNotFoundException;
import com.collicode.passenger.passengers.features.Mappings;
import com.collicode.passenger.passengers.models.Passenger;
import com.collicode.passenger.passengers.valueobjects.Age;
import com.collicode.passenger.passengers.valueobjects.Name;
import com.collicode.passenger.passengers.valueobjects.PassengerId;
import com.collicode.passenger.passengers.valueobjects.PassportNumber;
import org.springframework.stereotype.Service;

@Service
public class UpdatePassengerCommandHandler implements ICommandHandler<UpdatePassengerCommand, PassengerDto> {
    private final PassengerRepository passengerRepository;

    public UpdatePassengerCommandHandler(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public PassengerDto handle(UpdatePassengerCommand command) {

        PassengerEntity existingPassenger = passengerRepository.findPassengerByIdAndIsDeletedFalse(command.id());
        if (existingPassenger == null) {
            throw new PassengerNotFoundException();
        }

        Passenger passenger = Mappings.toPassengerAggregate(existingPassenger);

        passenger.update(
                new PassengerId(existingPassenger.getId()),
                new Name(command.name()),
                new PassportNumber(command.passportNumber()),
                command.passengerType(),
                new Age(command.age()),
                command.isDeleted()
        );

        PassengerEntity passengerEntity = Mappings.toPassengerEntity(passenger);

        PassengerEntity updatedPassenger = passengerRepository.save(passengerEntity);
        return Mappings.toPassengerDto(updatedPassenger);
    }
}