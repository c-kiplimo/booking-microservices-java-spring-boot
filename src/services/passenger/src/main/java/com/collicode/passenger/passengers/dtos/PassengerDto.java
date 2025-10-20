package com.collicode.passenger.passengers.dtos;


import com.collicode.passenger.passengers.enums.PassengerType;

import java.util.UUID;

public record PassengerDto(
        UUID id,
        String name,
        String passportNumber,
        PassengerType passengerType,
        int age
) {
}