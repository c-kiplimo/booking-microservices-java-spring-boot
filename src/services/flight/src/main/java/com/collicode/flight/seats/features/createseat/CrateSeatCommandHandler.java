package com.collicode.flight.seats.features.createseat;


import com.collicode.buildingblocks.mediator.abstractions.commands.ICommandHandler;
import com.collicode.flight.data.jpa.entities.SeatEntity;
import com.collicode.flight.data.jpa.repositories.SeatRepository;
import com.collicode.flight.seats.dtos.SeatDto;
import com.collicode.flight.seats.exceptions.SeatAlreadyExistException;
import com.collicode.flight.seats.features.Mappings;
import com.collicode.flight.seats.models.Seat;
import com.collicode.flight.seats.valueobjects.FlightId;
import com.collicode.flight.seats.valueobjects.SeatId;
import com.collicode.flight.seats.valueobjects.SeatNumber;
import org.springframework.stereotype.Service;

@Service
public class CrateSeatCommandHandler implements ICommandHandler<CreateSeatCommand, SeatDto> {

  private final SeatRepository seatRepository;

  public CrateSeatCommandHandler(SeatRepository seatRepository) {
    this.seatRepository = seatRepository;
  }

  @Override
  public SeatDto handle(CreateSeatCommand command) {

    SeatEntity existSeat = seatRepository.findSeatByIdAndIsDeletedFalse(command.id());
    if (existSeat!= null) {
      throw new SeatAlreadyExistException();
    }

    Seat seat = Seat.create(
      new SeatId(command.id()),
      new SeatNumber(command.seatNumber()),
      command.seatType(),
      command.seatClass(),
      new FlightId(command.flightId())
    );

    SeatEntity seatEntity = Mappings.toSeatEntity(seat);

    SeatEntity seatCreated = seatRepository.save(seatEntity);
    return Mappings.toSeatDto(seatCreated);
  }
}
