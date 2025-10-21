package com.collicode.flight.data.jpa.repositories;

import com.collicode.flight.data.jpa.entities.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface AirportRepository extends JpaRepository<AirportEntity, UUID> {
    AirportEntity findAirportByCodeAndIsDeletedFalse(String code);
}
