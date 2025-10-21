package com.collicode.flight.seats.features.reserveseat;


import com.collicode.buildingblocks.mediator.abstractions.commands.ICommandHandler;
import com.collicode.buildingblocks.mediator.abstractions.requests.Unit;
import com.collicode.flight.data.mongo.documents.SeatDocument;
import com.collicode.flight.data.mongo.repositories.SeatReadRepository;
import com.collicode.flight.seats.exceptions.SeatNumberAlreadyReservedException;
import com.collicode.flight.seats.features.Mappings;
import org.springframework.stereotype.Service;

@Service
public class ReserveSeatMongoCommandHandler implements ICommandHandler<ReserveSeatMongoCommand, Unit> {
  private final SeatReadRepository seatReadRepository;

  public ReserveSeatMongoCommandHandler(SeatReadRepository seatReadRepository) {
    this.seatReadRepository = seatReadRepository;
  }

  @Override
  public Unit handle(ReserveSeatMongoCommand command) {
    SeatDocument existSeat = seatReadRepository.findSeatByFlightIdAndSeatNumberAndIsDeletedFalse(command.flightId(), command.seatNumber());

    if (existSeat == null) {
      throw new SeatNumberAlreadyReservedException();
    }

    SeatDocument reservedSeatDocument = Mappings.toReserveSeatDocument(existSeat);
    seatReadRepository.save(reservedSeatDocument);

    return Unit.VALUE;
  }
}
