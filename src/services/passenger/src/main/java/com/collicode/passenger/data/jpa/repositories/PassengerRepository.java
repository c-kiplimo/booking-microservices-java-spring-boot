package com.collicode.passenger.data.jpa.repositories;


import com.collicode.passenger.data.jpa.entities.PassengerEntity;
import com.collicode.passenger.passengers.valueobjects.PassportNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface PassengerRepository extends JpaRepository<PassengerEntity, UUID> {
    PassengerEntity findPassengerByPassportNumberAndIsDeletedFalse(String passportNumber);

    PassengerEntity findPassengerByIdAndIsDeletedFalse(UUID id);

    List<PassengerEntity> findAllByIsDeletedFalse();

    PassengerEntity findPassengerByPassportNumber_PassportNumberAndIsDeletedFalse(String passportNumber);
}
