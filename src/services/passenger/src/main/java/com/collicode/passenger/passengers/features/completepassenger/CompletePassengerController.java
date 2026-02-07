package com.collicode.passenger.passengers.features.completepassenger;


import com.collicode.buildingblocks.mediator.abstractions.IMediator;
import com.collicode.passenger.passengers.dtos.PassengerDto;
import com.collicode.passenger.passengers.features.Mappings;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping(path = "api/v1/passenger")
@Tag(name = "passenger")
public class CompletePassengerController {

    private final IMediator mediator;

    public CompletePassengerController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PatchMapping("/{id}/complete")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Complete passenger registration", description = "Completes a passenger's profile with all required information")
    public ResponseEntity<PassengerDto> completePassenger(@PathVariable UUID id, @RequestBody CompletePassengerRequestDto completePassengerRequestDto) {
        CompletePassengerCommand command = Mappings.toCompletePassengerCommand(id, completePassengerRequestDto);
        PassengerDto result = this.mediator.send(command);
        return ResponseEntity.ok().body(result);
    }
}