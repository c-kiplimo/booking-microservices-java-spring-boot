package com.collicode.passenger.passengers.features.createpassenger;


import com.collicode.passenger.passengers.enums.PassengerType;

public record CreatePassengerRequestDto(
        String name,
        String PassportNumber,
        PassengerType passengerType,
        int age) {
}

