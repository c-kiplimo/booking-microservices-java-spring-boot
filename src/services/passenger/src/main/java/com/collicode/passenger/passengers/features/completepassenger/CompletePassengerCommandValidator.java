package com.collicode.passenger.passengers.features.completepassenger;


import com.collicode.passenger.passengers.enums.PassengerType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.EnumSet;


@Component
public class CompletePassengerCommandValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CompletePassengerCommand.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CompletePassengerCommand command = (CompletePassengerCommand) target;

        if (command.id() == null) {
            errors.rejectValue("id", "id.required", "Passenger ID is required");
        }

        if (command.name() == null || command.name().trim().isEmpty()) {
            errors.rejectValue("name", "name.required", "Name is required to complete registration");
        }

        if (command.passportNumber() == null || command.passportNumber().trim().isEmpty()) {
            errors.rejectValue("passportNumber", "passportNumber.required", "Passport number is required to complete registration");
        }

        if (command.age() <= 0) {
            errors.rejectValue("age", "age.invalid", "Age must be greater than 0");
        }

        if (command.passengerType() == null || !isValidPassengerType(command.passengerType())) {
            errors.rejectValue("passengerType", "passengerType.invalid",
                    "Passenger type must be Male, Female, or Baby");
        }
    }

    private boolean isValidPassengerType(PassengerType passengerType) {
        return EnumSet.of(
                PassengerType.Male,
                PassengerType.Female,
                PassengerType.Baby
        ).contains(passengerType);
    }
}