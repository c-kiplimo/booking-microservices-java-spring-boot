package com.collicode.buildingblocks.contracts.booking;


import com.collicode.buildingblocks.core.event.IntegrationEvent;

import java.util.UUID;

public record BookingCreated(UUID Id) implements IntegrationEvent {
}

