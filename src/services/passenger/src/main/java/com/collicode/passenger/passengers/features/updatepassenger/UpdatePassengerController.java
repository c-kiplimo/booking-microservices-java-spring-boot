package com.collicode.passenger.passengers.features.updatepassenger;


import com.collicode.buildingblocks.mediator.abstractions.IMediator;
import com.collicode.passenger.passengers.dtos.PassengerDto;
import com.collicode.passenger.passengers.features.Mappings;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping(path = "api/v1/passenger")
@Tag(name = "passenger")
public class UpdatePassengerController {

    private final IMediator mediator;

    public UpdatePassengerController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PassengerDto> updatePassenger(@PathVariable UUID id, @RequestBody UpdatePassengerRequestDto updatePassengerRequestDto) {
        UpdatePassengerCommand command = Mappings.toUpdatePassengerCommand(id, updatePassengerRequestDto);
        this.mediator.send(command);
        return ResponseEntity.noContent().build();
    }
}