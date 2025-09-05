package com.collicode.buildingblocks.contracts.passenger;


import com.collicode.buildingblocks.core.event.IntegrationEvent;

import java.util.UUID;

public record PassengerCreated(UUID Id) implements IntegrationEvent {
}
