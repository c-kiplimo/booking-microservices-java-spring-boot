package com.collicode.passenger.passengers.features.deletepassenger;


import com.collicode.buildingblocks.core.event.DomainEvent;
import com.collicode.passenger.passengers.enums.PassengerType;

import java.util.UUID;


public record PassengerDeletedDomainEvent(
        UUID id,
        String name,
        String passportNumber,
        PassengerType passengerType,
        int age,
        boolean isDeleted) implements DomainEvent {
}