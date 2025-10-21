package com.collicode.flight.seats.models;


import com.collicode.buildingblocks.core.model.AggregateRoot;
import com.collicode.flight.seats.enums.SeatClass;
import com.collicode.flight.seats.enums.SeatType;
import com.collicode.flight.seats.features.createseat.SeatCreatedDomainEvent;
import com.collicode.flight.seats.features.reserveseat.SeatReservedDomainEvent;
import com.collicode.flight.seats.valueobjects.FlightId;
import com.collicode.flight.seats.valueobjects.SeatId;
import com.collicode.flight.seats.valueobjects.SeatNumber;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter(AccessLevel.PRIVATE)
public class Seat extends AggregateRoot<SeatId> {
  SeatNumber seatNumber;
  SeatType seatType;
  SeatClass seatClass;
  FlightId flightId;


  public Seat(SeatId seatId, SeatNumber seatNumber, SeatType seatType, SeatClass seatClass, FlightId flightId, LocalDateTime createdAt, Long createdBy, LocalDateTime lastModified, Long lastModifiedBy, Long version, boolean isDeleted) {
    this.id = seatId;
    this.seatNumber = seatNumber;
    this.seatType = seatType;
    this.seatClass = seatClass;
    this.flightId = flightId;
    this.createdAt = createdAt;
    this.createdBy = createdBy;
    this.lastModified = lastModified;
    this.lastModifiedBy = lastModifiedBy;
    this.version = version;
    this.isDeleted = isDeleted;
  }


  public Seat(SeatId seatId, SeatNumber seatNumber, SeatType seatType, SeatClass seatClass, FlightId flightId) {
    this.id = seatId;
    this.seatNumber = seatNumber;
    this.seatType = seatType;
    this.seatClass = seatClass;
    this.flightId = flightId;
  }

  public static Seat create(SeatId seatId, SeatNumber seatNumber, SeatType seatType, SeatClass seatClass, FlightId flightId) {
    var seat = new Seat(seatId, seatNumber, seatType, seatClass, flightId);

    seat.addDomainEvent(new SeatCreatedDomainEvent(
      seat.id.getSeatId(),
      seat.seatNumber.getSeatNumber(),
      seat.seatType,
      seat.seatClass,
      seat.flightId.getFlightId(),
      false
    ));

    return seat;
  }

  public void reserveSeat() {
    this.isDeleted = true;

    this.addDomainEvent(new SeatReservedDomainEvent(
      this.getId().getSeatId(),
      this.getSeatNumber().getSeatNumber(),
      this.getSeatType(),
      this.getSeatClass(),
      this.getFlightId().getFlightId(),
      true
      ));
  }
}
