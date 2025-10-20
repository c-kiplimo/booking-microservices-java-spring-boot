package com.collicode.passenger;


import com.collicode.buildingblocks.contracts.passenger.PassengerCreated;
import com.collicode.buildingblocks.core.event.DomainEvent;
import com.collicode.buildingblocks.core.event.EventMapper;
import com.collicode.buildingblocks.core.event.IntegrationEvent;
import com.collicode.buildingblocks.core.event.InternalCommand;
import com.collicode.passenger.passengers.features.createpassenger.CreatePassengerMongoCommand;
import com.collicode.passenger.passengers.features.createpassenger.PassengerCreatedDomainEvent;
import org.springframework.stereotype.Component;

@Component
public class EventMapperImpl implements EventMapper {
    @Override
    public IntegrationEvent MapToIntegrationEvent(DomainEvent event) {
        return switch (event) {
            case PassengerCreatedDomainEvent e -> new PassengerCreated(e.id());
            default -> null;
        };
    }

    @Override
    public InternalCommand MapToInternalCommand(DomainEvent event) {
        return switch (event) {
            case PassengerCreatedDomainEvent e ->
                    new CreatePassengerMongoCommand(e.id(), e.name(), e.passportNumber(), e.passengerType(), e.age(), e.isDeleted());
            default -> null;
        };
    }
}
