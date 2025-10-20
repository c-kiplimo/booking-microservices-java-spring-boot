package com.collicode.passenger.passengers.features.createpassenger;


import com.collicode.buildingblocks.core.event.DomainEvent;
import com.collicode.passenger.passengers.enums.PassengerType;

import java.util.UUID;

public record PassengerCreatedDomainEvent(
        UUID id,
        String name,
        String passportNumber,
        PassengerType passengerType,
        int age,
        boolean isDeleted) implements DomainEvent {
}