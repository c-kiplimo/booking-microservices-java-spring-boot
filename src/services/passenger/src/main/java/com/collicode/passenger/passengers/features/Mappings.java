package com.collicode.passenger.passengers.features;

import com.collicode.passenger.data.jpa.entities.PassengerEntity;
import com.collicode.passenger.data.mongo.documents.PassengerDocument;
import com.collicode.passenger.passengers.dtos.PassengerDto;
import com.collicode.passenger.passengers.enums.PassengerType;
import com.collicode.passenger.passengers.features.completepassenger.CompletePassengerCommand;
import com.collicode.passenger.passengers.features.completepassenger.CompletePassengerMongoCommand;
import com.collicode.passenger.passengers.features.completepassenger.CompletePassengerRequestDto;
import com.collicode.passenger.passengers.features.createpassenger.CreatePassengerCommand;
import com.collicode.passenger.passengers.features.createpassenger.CreatePassengerMongoCommand;
import com.collicode.passenger.passengers.features.createpassenger.CreatePassengerRequestDto;
import com.collicode.passenger.passengers.features.deletepassenger.DeletePassengerMongoCommand;
import com.collicode.passenger.passengers.features.updatepassenger.UpdatePassengerCommand;
import com.collicode.passenger.passengers.features.updatepassenger.UpdatePassengerMongoCommand;
import com.collicode.passenger.passengers.features.updatepassenger.UpdatePassengerRequestDto;
import com.collicode.passenger.passengers.models.Passenger;
import com.collicode.passenger.passengers.valueobjects.Age;
import com.collicode.passenger.passengers.valueobjects.Name;
import com.collicode.passenger.passengers.valueobjects.PassengerId;
import com.collicode.passenger.passengers.valueobjects.PassportNumber;
import com.github.f4b6a3.uuid.UuidCreator;
import org.bson.types.ObjectId;

import java.util.UUID;


public final class Mappings {

    public static PassengerEntity toPassengerEntity(Passenger passenger) {
        return new PassengerEntity(
                passenger.getId().getPassengerId(),
                passenger.getName(),
                passenger.getPassportNumber(),
                passenger.getPassengerType(),
                passenger.getAge(),
                passenger.getCreatedAt(),
                passenger.getCreatedBy(),
                passenger.getLastModified(),
                passenger.getLastModifiedBy(),
                passenger.getVersion(),
                passenger.isDeleted()
        );
    }

    public static Passenger toPassengerAggregate(PassengerEntity passengerEntity) {
        return new Passenger(
                new PassengerId(passengerEntity.getId()),
                passengerEntity.getName(),
                passengerEntity.getPassportNumber(),
                passengerEntity.getPassengerType(),
                passengerEntity.getAge(),
                passengerEntity.getCreatedAt(),
                passengerEntity.getCreatedBy(),
                passengerEntity.getLastModified(),
                passengerEntity.getLastModifiedBy(),
                passengerEntity.getVersion(),
                passengerEntity.isDeleted()
        );
    }

    public static PassengerDto toPassengerDto(PassengerEntity passengerEntity) {
        return new PassengerDto(
                passengerEntity.getId(),
                passengerEntity.getName().getName(),
                passengerEntity.getPassportNumber().getPassportNumber(),
                passengerEntity.getPassengerType(),
                passengerEntity.getAge().getAge());
    }


    public static PassengerDocument toPassengerDocument(CreatePassengerMongoCommand createPassengerMongoCommand) {
        return new PassengerDocument(
                createPassengerMongoCommand.id(),
                createPassengerMongoCommand.name(),
                createPassengerMongoCommand.passportNumber(),
                createPassengerMongoCommand.passengerType(),
                createPassengerMongoCommand.age(),
                createPassengerMongoCommand.isDeleted()
        );
    }

    public static PassengerDocument toPassengerDocument(PassengerEntity passengerEntity) {
        return new PassengerDocument(
                passengerEntity.getId(),
                passengerEntity.getName().getName(),
                passengerEntity.getPassportNumber().getPassportNumber(),
                passengerEntity.getPassengerType(),
                passengerEntity.getAge().getAge(),
                passengerEntity.isDeleted()
        );
    }

    public static PassengerDocument toPassengerDocument(ObjectId id, UpdatePassengerMongoCommand updatePassengerMongoCommand) {
        return new PassengerDocument(
                id,
                updatePassengerMongoCommand.id(),
                updatePassengerMongoCommand.name(),
                updatePassengerMongoCommand.passportNumber(),
                updatePassengerMongoCommand.passengerType(),
                updatePassengerMongoCommand.age(),
                updatePassengerMongoCommand.isDeleted()
        );
    }

    public static PassengerDocument toPassengerDocument(DeletePassengerMongoCommand deletePassengerMongoCommand) {
        return new PassengerDocument(
                deletePassengerMongoCommand.id(),
                deletePassengerMongoCommand.name(),
                deletePassengerMongoCommand.passportNumber(),
                deletePassengerMongoCommand.passengerType(),
                deletePassengerMongoCommand.age(),
                deletePassengerMongoCommand.isDeleted()
        );
    }

    public static PassengerDocument toPassengerDocument(ObjectId id, CompletePassengerMongoCommand completePassengerMongoCommand) {
        return new PassengerDocument(
                id,
                completePassengerMongoCommand.id(),
                completePassengerMongoCommand.name(),
                completePassengerMongoCommand.passportNumber(),
                completePassengerMongoCommand.passengerType(),
                completePassengerMongoCommand.age(),
                false
        );
    }

    public static CreatePassengerCommand toCreatePassengerCommand(CreatePassengerRequestDto passengerRequestDto) {
        return new CreatePassengerCommand(
                UuidCreator.getTimeOrderedEpoch(),
                passengerRequestDto.name(),
                passengerRequestDto.PassportNumber(),
                passengerRequestDto.passengerType(),
                passengerRequestDto.age()
        );
    }

    public static UpdatePassengerCommand toUpdatePassengerCommand(UUID id, UpdatePassengerRequestDto updatePassengerRequestDto) {
        return new UpdatePassengerCommand(
                id,
                updatePassengerRequestDto.name(),
                updatePassengerRequestDto.passportNumber(),
                updatePassengerRequestDto.passengerType(),
                updatePassengerRequestDto.age(),
                updatePassengerRequestDto.isDeleted()
        );
    }

    public static CompletePassengerCommand toCompletePassengerCommand(UUID id, CompletePassengerRequestDto completePassengerRequestDto) {
        return new CompletePassengerCommand(
                id,
                completePassengerRequestDto.name(),
                completePassengerRequestDto.passportNumber(),
                completePassengerRequestDto.passengerType(),
                completePassengerRequestDto.age()
        );
    }


    public static PassengerDto toPassengerDto(PassengerDocument passengerDocument) {
        return new PassengerDto(
                passengerDocument.getPassengerId(),
                passengerDocument.getName(),
                passengerDocument.getPassportNumber(),
                passengerDocument.getPassengerType(),
                passengerDocument.getAge()
        );
    }

    public static passenger.Passenger.PassengerResponseDto toPassengerResponseDtoGrpc(PassengerDto passengerDto) {

        return passenger.Passenger.PassengerResponseDto.newBuilder()
                .setId(passengerDto.id().toString())
                .setName(passengerDto.name())
                .setPassportNumber(passengerDto.passportNumber())
                .setPassengerType(toPassengerTypeGrpc(passengerDto.passengerType()))
                .setAge(passengerDto.age())
                .build();
    }

    public static passenger.Passenger.PassengerType toPassengerTypeGrpc(PassengerType passengerType) {
        return switch (passengerType) {
            case Male -> passenger.Passenger.PassengerType.PASSENGER_TYPE_MALE;
            case Female -> passenger.Passenger.PassengerType.PASSENGER_TYPE_FEMALE;
            case Baby -> passenger.Passenger.PassengerType.PASSENGER_TYPE_BABY;
        };
    }
}