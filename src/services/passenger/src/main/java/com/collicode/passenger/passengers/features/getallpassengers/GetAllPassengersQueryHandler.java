package com.collicode.passenger.passengers.features.getallpassengers;


import com.collicode.buildingblocks.mediator.abstractions.queries.IQueryHandler;
import com.collicode.passenger.data.mongo.documents.PassengerDocument;
import com.collicode.passenger.data.mongo.repositories.PassengerReadRepository;
import com.collicode.passenger.passengers.dtos.PassengerDto;
import com.collicode.passenger.passengers.features.Mappings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllPassengersQueryHandler implements IQueryHandler<GetAllPassengersQuery, List<PassengerDto>> {
    private final PassengerReadRepository passengerReadRepository;

    public GetAllPassengersQueryHandler(PassengerReadRepository passengerReadRepository) {
        this.passengerReadRepository = passengerReadRepository;
    }

    @Override
    public List<PassengerDto> handle(GetAllPassengersQuery query) {
        List<PassengerDocument> passengerDocuments = passengerReadRepository.findAllByIsDeletedFalse();
        return passengerDocuments.stream().map(Mappings::toPassengerDto).toList();
    }
}