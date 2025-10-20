package com.collicode.passenger.passengers.valueobjects;


import com.collicode.buildingblocks.utils.validation.ValidationUtils;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Embeddable
@EqualsAndHashCode
@NoArgsConstructor // Required by JPA
@Getter
public class PassengerId {
    private UUID passengerId;

    public PassengerId(UUID value) {
        ValidationUtils.notBeNullOrEmpty(value);

        this.passengerId = value;
    }
}
