package com.collicode.booking.bookings.valueobjects;

import com.collicode.buildingblocks.utils.validation.ValidationUtils;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor // Required by JPA
@Getter
public class PassengerInfo {
    private String name;

    public PassengerInfo(String name) {
        ValidationUtils.notBeNullOrEmpty(name);

        this.name = name;
    }
}
