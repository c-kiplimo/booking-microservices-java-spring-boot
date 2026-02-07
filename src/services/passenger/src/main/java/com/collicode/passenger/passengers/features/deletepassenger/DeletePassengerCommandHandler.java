package com.collicode.passenger.passengers.features.deletepassenger;


import com.collicode.buildingblocks.mediator.abstractions.commands.ICommandHandler;
import com.collicode.passenger.data.jpa.entities.PassengerEntity;
import com.collicode.passenger.data.jpa.repositories.PassengerRepository;
import com.collicode.passenger.passengers.dtos.PassengerDto;
import com.collicode.passenger.passengers.exceptions.PassengerNotFoundException;
import com.collicode.passenger.passengers.features.Mappings;
import com.collicode.passenger.passengers.models.Passenger;
import org.springframework.stereotype.Component;

@Component
public class DeletePassengerCommandHandler implements ICommandHandler<DeletePassengerCommand, PassengerDto> {
    private final PassengerRepository passengerRepository;

    public DeletePassengerCommandHandler(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public PassengerDto handle(DeletePassengerCommand command) {

        PassengerEntity existingPassenger = passengerRepository.findPassengerByIdAndIsDeletedFalse(command.id());
        if (existingPassenger == null) {
            throw new PassengerNotFoundException();
        }

        Passenger passenger = Mappings.toPassengerAggregate(existingPassenger);

        passenger.delete();

        PassengerEntity passengerEntity = Mappings.toPassengerEntity(passenger);

        PassengerEntity updatedPassenger = passengerRepository.save(passengerEntity);
        return Mappings.toPassengerDto(updatedPassenger);
    }
}