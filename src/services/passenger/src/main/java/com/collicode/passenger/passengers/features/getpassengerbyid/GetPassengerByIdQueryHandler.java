package com.collicode.passenger.passengers.features.getpassengerbyid;


import com.collicode.buildingblocks.mediator.abstractions.queries.IQueryHandler;
import com.collicode.passenger.data.mongo.documents.PassengerDocument;
import com.collicode.passenger.data.mongo.repositories.PassengerReadRepository;
import com.collicode.passenger.passengers.dtos.PassengerDto;
import com.collicode.passenger.passengers.features.Mappings;
import org.springframework.stereotype.Service;

@Service
public class GetPassengerByIdQueryHandler implements IQueryHandler<GetPassengerByIdQuery, PassengerDto> {
    private final PassengerReadRepository passengerReadRepository;

    public GetPassengerByIdQueryHandler(PassengerReadRepository passengerReadRepository) {
        this.passengerReadRepository = passengerReadRepository;
    }

    @Override
    public PassengerDto handle(GetPassengerByIdQuery query) {
        PassengerDocument passenger = passengerReadRepository.findPassengerByPassengerIdAndIsDeletedFalse(query.id());
        return Mappings.toPassengerDto(passenger);
    }
}
