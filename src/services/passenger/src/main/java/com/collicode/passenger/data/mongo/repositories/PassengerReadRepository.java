package com.collicode.passenger.data.mongo.repositories;


import com.collicode.passenger.data.mongo.documents.PassengerDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface PassengerReadRepository extends MongoRepository<PassengerDocument, ObjectId> {
    PassengerDocument findPassengerByPassengerIdAndIsDeletedFalse(UUID passengerId);

    List<PassengerDocument> findAllByIsDeletedFalse();

    PassengerDocument findByPassportNumberAndIsDeletedFalse(String passportNumber);
}

