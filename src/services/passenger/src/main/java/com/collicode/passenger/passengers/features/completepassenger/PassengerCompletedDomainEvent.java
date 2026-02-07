package com.collicode.passenger.passengers.features.completepassenger;


import com.collicode.buildingblocks.core.event.DomainEvent;
import com.collicode.passenger.passengers.enums.PassengerType;

import java.util.UUID;


public record PassengerCompletedDomainEvent(
        UUID id,
        String name,
        String passportNumber,
        PassengerType passengerType,
        int age,
        boolean isCompleted) implements DomainEvent {
}