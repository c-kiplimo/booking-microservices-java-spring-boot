package com.collicode.booking;


import com.collicode.booking.bookings.features.createbooking.BookingCreatedDomainEvent;
import com.collicode.booking.bookings.features.createbooking.CreateBookingMongoCommand;
import com.collicode.buildingblocks.contracts.booking.BookingCreated;
import com.collicode.buildingblocks.core.event.DomainEvent;
import com.collicode.buildingblocks.core.event.EventMapper;
import com.collicode.buildingblocks.core.event.IntegrationEvent;
import com.collicode.buildingblocks.core.event.InternalCommand;
import org.springframework.stereotype.Component;

@Component
public class EventMapperImpl implements EventMapper {
    @Override
    public IntegrationEvent MapToIntegrationEvent(DomainEvent event) {
        return switch (event) {
            case BookingCreatedDomainEvent e -> new BookingCreated(e.id());
            default -> null;
        };
    }

    @Override
    public InternalCommand MapToInternalCommand(DomainEvent event) {
        return switch (event) {
            case BookingCreatedDomainEvent e ->
                    new CreateBookingMongoCommand(e.id(), e.passengerInfo(), e.trip(), e.isDeleted());
            default -> null;
        };
    }
}

