package com.collicode.passenger.passengers.features.getallpassengers;


import com.collicode.buildingblocks.mediator.abstractions.IMediator;
import com.collicode.passenger.passengers.dtos.PassengerDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/passenger")
@Tag(name = "passenger")
public class GetAllPassengersController {

    private final IMediator mediator;

    public GetAllPassengersController(IMediator mediator) {
        this.mediator = mediator;
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<PassengerDto>> getAllPassengers() {
        List<PassengerDto> result = this.mediator.send(new GetAllPassengersQuery());
        return ResponseEntity.ok().body(result);
    }
}