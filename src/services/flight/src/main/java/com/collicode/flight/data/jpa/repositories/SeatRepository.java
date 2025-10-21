package com.collicode.flight.data.jpa.repositories;


import com.collicode.flight.data.jpa.entities.SeatEntity;
import com.collicode.flight.seats.valueobjects.FlightId;
import com.collicode.flight.seats.valueobjects.SeatNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, UUID> {
    SeatEntity findSeatByIdAndIsDeletedFalse(UUID id);

    SeatEntity findSeatByFlightIdAndSeatNumberAndIsDeletedFalse(FlightId flightId, SeatNumber seatNumber);
}
