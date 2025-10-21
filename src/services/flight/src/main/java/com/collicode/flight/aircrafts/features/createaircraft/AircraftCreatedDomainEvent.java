package com.collicode.flight.aircrafts.features.createaircraft;


import com.collicode.buildingblocks.core.event.DomainEvent;

import java.util.UUID;

public record AircraftCreatedDomainEvent(
  UUID id,
  String name,
  String model,
  int manufacturingYear,
  boolean isDeleted) implements DomainEvent {
}
