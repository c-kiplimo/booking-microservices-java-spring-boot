package com.collicode.passenger.passengers.features.updatepassenger;


import com.collicode.passenger.passengers.enums.PassengerType;

public record UpdatePassengerRequestDto(
        String name,
        String passportNumber,
        PassengerType passengerType,
        int age,
        boolean isDeleted) {
}