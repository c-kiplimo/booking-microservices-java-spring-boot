package com.collicode.flight.seats.features.getavailableseats;


import com.collicode.buildingblocks.mediator.abstractions.queries.IQueryHandler;
import com.collicode.flight.data.mongo.documents.SeatDocument;
import com.collicode.flight.data.mongo.repositories.SeatReadRepository;
import com.collicode.flight.seats.dtos.SeatDto;
import com.collicode.flight.seats.features.Mappings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAvailableSeatsQueryHandler implements IQueryHandler<GetAvailableSeatsQuery, List<SeatDto>> {
  private final SeatReadRepository seatReadRepository;

  public GetAvailableSeatsQueryHandler(SeatReadRepository seatReadRepository) {
    this.seatReadRepository = seatReadRepository;
  }

  @Override
  public List<SeatDto> handle(GetAvailableSeatsQuery query) {
    List<SeatDocument> seats = seatReadRepository.findAllSeatsByFlightIdAndIsDeletedFalse(query.flightId());
    return seats.stream().map(Mappings::toSeatDto).toList();
  }
}

