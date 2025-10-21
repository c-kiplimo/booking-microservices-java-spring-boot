package com.collicode.booking.data.jpa.repositories;

import com.collicode.booking.data.jpa.entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {
    BookingEntity findBookingByIdAndIsDeletedFalse(UUID id);
}
