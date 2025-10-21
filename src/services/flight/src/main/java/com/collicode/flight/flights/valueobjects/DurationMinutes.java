package com.collicode.flight.flights.valueobjects;

import com.collicode.buildingblocks.utils.validation.ValidationUtils;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Embeddable
@EqualsAndHashCode
@NoArgsConstructor // Required by JPA
@Getter
public class DurationMinutes {
  private BigDecimal durationMinutes;

  public DurationMinutes(BigDecimal value) {
    ValidationUtils.notBeNegativeOrNull(value);

    this.durationMinutes = value;
  }
}

