package com.collicode.flight.flights.features.getflightbyid;


import com.collicode.buildingblocks.mediator.abstractions.IMediator;
import com.collicode.flight.flights.dtos.FlightDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/flight")
@Tag(name = "flight")
public class GetFlightByIdController {

  private final IMediator mediator;

  public GetFlightByIdController(IMediator mediator) {
    this.mediator = mediator;
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<FlightDto> getFlightById(@PathVariable UUID id) {
    FlightDto result = this.mediator.send(new GetFlightByIdQuery(id));
    return ResponseEntity.ok().body(result);
  }
}
