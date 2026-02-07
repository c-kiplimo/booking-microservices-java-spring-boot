package com.collicode.passenger.passengers.features.updatepassenger;


import com.collicode.passenger.passengers.enums.PassengerType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.EnumSet;


@Component
public class UpdatePassengerCommandValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UpdatePassengerCommand.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UpdatePassengerCommand command = (UpdatePassengerCommand) target;

        if (command.id() == null) {
            errors.rejectValue("id", "id.required", "Passenger ID is required");
        }

        if (command.name() == null || command.name().trim().isEmpty()) {
            errors.rejectValue("name", "name.required", "Name is required");
        }

        if (command.passportNumber() == null || command.passportNumber().trim().isEmpty()) {
            errors.rejectValue("passportNumber", "passportNumber.required", "Passport number is required");
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