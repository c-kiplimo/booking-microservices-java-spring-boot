package com.collicode.buildingblocks.contracts.flight;


import com.collicode.buildingblocks.core.event.IntegrationEvent;

import java.util.UUID;

public record SeatReserved(UUID Id) implements IntegrationEvent {
}

