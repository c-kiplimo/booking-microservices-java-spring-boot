package com.collicode.passenger.passengers.features.deletepassenger;


import com.collicode.buildingblocks.mediator.abstractions.IMediator;
import com.collicode.passenger.passengers.dtos.PassengerDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping(path = "api/v1/passenger")
@Tag(name = "passenger")
public class DeletePassengerController {

    private final IMediator mediator;

    public DeletePassengerController(IMediator mediator) {
        this.mediator = mediator;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PassengerDto> deletePassenger(@PathVariable UUID id) {
        this.mediator.send(new DeletePassengerCommand(id));
        return ResponseEntity.noContent().build();
    }
}