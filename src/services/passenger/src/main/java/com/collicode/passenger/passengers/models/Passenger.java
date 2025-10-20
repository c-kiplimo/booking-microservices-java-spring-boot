package com.collicode.passenger.passengers.models;


import com.collicode.buildingblocks.core.model.AggregateRoot;
import com.collicode.passenger.passengers.enums.PassengerType;
import com.collicode.passenger.passengers.features.createpassenger.PassengerCreatedDomainEvent;
import com.collicode.passenger.passengers.valueobjects.Age;
import com.collicode.passenger.passengers.valueobjects.Name;
import com.collicode.passenger.passengers.valueobjects.PassengerId;
import com.collicode.passenger.passengers.valueobjects.PassportNumber;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter(AccessLevel.PRIVATE)
public class Passenger extends AggregateRoot<PassengerId> {
    Name name;
    PassportNumber passportNumber;
    PassengerType passengerType;
    Age age;

    public Passenger(PassengerId passengerId, Name name, PassportNumber passportNumber, PassengerType passengerType, Age age, LocalDateTime createdAt, Long createdBy, LocalDateTime lastModified, Long lastModifiedBy, Long version, boolean isDeleted) {
        this.id = passengerId;
        this.name = name;
        this.passportNumber = passportNumber;
        this.passengerType = passengerType;
        this.age = age;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.lastModified = lastModified;
        this.lastModifiedBy = lastModifiedBy;
        this.version = version;
        this.isDeleted = isDeleted;
    }

    public Passenger(PassengerId passengerId, Name name, PassportNumber passportNumber, PassengerType passengerType, Age age) {
        this.id = passengerId;
        this.name = name;
        this.passportNumber = passportNumber;
        this.passengerType = passengerType;
        this.age = age;
    }

    public static Passenger create(PassengerId passengerId, Name name, PassportNumber passportNumber, PassengerType passengerType, Age age) {
        var passenger = new Passenger(passengerId, name, passportNumber, passengerType, age);

        passenger.addDomainEvent(new PassengerCreatedDomainEvent(
                passenger.id.getPassengerId(),
                passenger.name.getName(),
                passenger.passportNumber.getPassportNumber(),
                passenger.passengerType,
                passenger.age.getAge(),
                false
        ));

        return passenger;
    }
}
