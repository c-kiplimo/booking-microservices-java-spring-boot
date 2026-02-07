package com.collicode.passenger.passengers.features.updatepassenger;


import com.collicode.buildingblocks.core.event.DomainEvent;
import com.collicode.passenger.passengers.enums.PassengerType;

import java.util.UUID;


public record PassengerUpdatedDomainEvent(
        UUID id,
        String name,
        String passportNumber,
        PassengerType passengerType,
        int age,
        boolean isDeleted) implements DomainEvent {
}