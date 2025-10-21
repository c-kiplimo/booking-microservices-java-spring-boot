package com.collicode.flight.data.jpa.repositories;

import com.collicode.flight.data.jpa.entities.AircraftEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AircraftRepository extends JpaRepository<AircraftEntity, UUID> {
  AircraftEntity findAircraftByModelAndIsDeletedFalse(String model);
}
