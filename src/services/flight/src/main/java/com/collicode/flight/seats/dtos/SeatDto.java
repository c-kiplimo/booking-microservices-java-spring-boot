package com.collicode.flight.seats.dtos;


import com.collicode.flight.seats.enums.SeatClass;
import com.collicode.flight.seats.enums.SeatType;

import java.util.UUID;

public record SeatDto(
        UUID id,
        String seatNumber,
        SeatType seatType,
        SeatClass seatClass,
        UUID flightId
) {
}
