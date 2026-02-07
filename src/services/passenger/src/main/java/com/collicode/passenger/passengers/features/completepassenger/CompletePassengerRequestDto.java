package com.collicode.passenger.passengers.features.completepassenger;


import com.collicode.passenger.passengers.enums.PassengerType;

public record CompletePassengerRequestDto(
        String name,
        String passportNumber,
        PassengerType passengerType,
        int age) {
}