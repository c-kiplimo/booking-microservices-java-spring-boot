package com.collicode.flight.aircrafts.models;


import com.collicode.buildingblocks.core.model.AggregateRoot;
import com.collicode.flight.aircrafts.features.createaircraft.AircraftCreatedDomainEvent;
import com.collicode.flight.aircrafts.valueobjects.AircraftId;
import com.collicode.flight.aircrafts.valueobjects.ManufacturingYear;
import com.collicode.flight.aircrafts.valueobjects.Model;
import com.collicode.flight.aircrafts.valueobjects.Name;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter(AccessLevel.PRIVATE)
public class Aircraft extends AggregateRoot<AircraftId> {
  Name name;
  Model model;
  ManufacturingYear manufacturingYear;


  public Aircraft(AircraftId aircraftId, Name name, Model model, ManufacturingYear manufacturingYear, LocalDateTime createdAt, Long createdBy, LocalDateTime lastModified, Long lastModifiedBy, Long version, boolean isDeleted) {
    this.id = aircraftId;
    this.name = name;
    this.model = model;
    this.manufacturingYear = manufacturingYear;
    this.createdAt = createdAt;
    this.createdBy = createdBy;
    this.lastModified = lastModified;
    this.lastModifiedBy = lastModifiedBy;
    this.version = version;
    this.isDeleted = isDeleted;
  }

  public Aircraft(AircraftId aircraftId, Name name, Model model, ManufacturingYear manufacturingYear) {
    this.id = aircraftId;
    this.name = name;
    this.model = model;
    this.manufacturingYear = manufacturingYear;
  }

  public static Aircraft create(AircraftId id, Name name, Model model, ManufacturingYear manufacturingYear) {
    var aircraft = new Aircraft(id, name, model, manufacturingYear);

    aircraft.addDomainEvent(new AircraftCreatedDomainEvent(
      aircraft.id.getAircraftId(),
      aircraft.name.getName(),
      aircraft.model.getModel(),
      aircraft.manufacturingYear.getManufacturingYear(),
      false
    ));

    return aircraft;
  }
}
