package com.collicode.booking.bookings.modles;


import com.collicode.booking.bookings.features.createbooking.BookingCreatedDomainEvent;
import com.collicode.booking.bookings.valueobjects.BookingId;
import com.collicode.booking.bookings.valueobjects.PassengerInfo;
import com.collicode.booking.bookings.valueobjects.Trip;
import com.collicode.buildingblocks.core.model.AggregateRoot;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter(AccessLevel.PRIVATE)
public class Booking extends AggregateRoot<BookingId> {

    PassengerInfo passengerInfo;
    Trip trip;

    public Booking(BookingId bookingId, PassengerInfo passengerInfo, Trip trip, LocalDateTime createdAt, Long createdBy, LocalDateTime lastModified, Long lastModifiedBy, Long version, boolean isDeleted) {
        this.id = bookingId;
        this.passengerInfo = passengerInfo;
        this.trip = trip;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.lastModified = lastModified;
        this.lastModifiedBy = lastModifiedBy;
        this.version = version;
        this.isDeleted = isDeleted;
    }

    public Booking(BookingId bookingId, PassengerInfo passengerInfo, Trip trip) {
        this.id = bookingId;
        this.passengerInfo = passengerInfo;
        this.trip = trip;
    }

    public static Booking create(BookingId bookingId, PassengerInfo passengerInfo, Trip trip) {
        var booking = new Booking(bookingId, passengerInfo, trip);

        booking.addDomainEvent(new BookingCreatedDomainEvent(
                booking.getId().getBookingId(),
                booking.passengerInfo,
                booking.trip,
                false
        ));

        return booking;
    }
}
