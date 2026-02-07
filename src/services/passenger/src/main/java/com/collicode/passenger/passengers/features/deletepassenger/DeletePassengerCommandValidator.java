package com.collicode.passenger.passengers.features.deletepassenger;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DeletePassengerCommandValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return DeletePassengerCommand.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DeletePassengerCommand command = (DeletePassengerCommand) target;

        if (command.id() == null) {
            errors.rejectValue("id", "id.required", "Passenger ID is required");
        }
    }
}