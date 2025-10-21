package com.collicode.flight.seats.features.createseat;



import com.collicode.buildingblocks.mediator.abstractions.commands.ICommandHandler;
import com.collicode.buildingblocks.mediator.abstractions.requests.Unit;
import com.collicode.flight.data.mongo.documents.SeatDocument;
import com.collicode.flight.data.mongo.repositories.SeatReadRepository;
import com.collicode.flight.seats.exceptions.SeatAlreadyExistException;
import com.collicode.flight.seats.features.Mappings;
import org.springframework.stereotype.Service;


@Service
public class CreateSeatMongoCommandHandler implements ICommandHandler<CreateSeatMongoCommand, Unit> {

  private final SeatReadRepository seatReadRepository;

  public CreateSeatMongoCommandHandler(SeatReadRepository seatReadRepository) {
    this.seatReadRepository = seatReadRepository;
  }

  public Unit handle(CreateSeatMongoCommand command) {

    SeatDocument seatDocument = Mappings.toSeatDocument(command);

    var seatExist = seatReadRepository.findSeatByFlightIdAndSeatNumberAndIsDeletedFalse(seatDocument.getFlightId(), seatDocument.getSeatNumber());

    if (seatExist != null) {
      throw new SeatAlreadyExistException();
    }

    seatReadRepository.save(seatDocument);

    return Unit.VALUE;
  }
}

