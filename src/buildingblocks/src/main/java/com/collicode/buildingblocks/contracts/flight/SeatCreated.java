package com.collicode.buildingblocks.contracts.flight;


import com.collicode.buildingblocks.core.event.IntegrationEvent;

import java.util.UUID;

public record SeatCreated(UUID Id) implements IntegrationEvent {
}




