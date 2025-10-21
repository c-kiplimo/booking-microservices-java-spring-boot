package com.collicode.flight.seats.features.reserveseat;


import com.collicode.buildingblocks.mediator.abstractions.commands.ICommandHandler;
import com.collicode.flight.data.jpa.entities.SeatEntity;
import com.collicode.flight.data.jpa.repositories.SeatRepository;
import com.collicode.flight.seats.dtos.SeatDto;
import com.collicode.flight.seats.exceptions.SeatNumberAlreadyReservedException;
import com.collicode.flight.seats.features.Mappings;
import com.collicode.flight.seats.models.Seat;
import com.collicode.flight.seats.valueobjects.FlightId;
import com.collicode.flight.seats.valueobjects.SeatNumber;
import org.springframework.stereotype.Service;


@Service
public class ReserveSeatCommandHandler implements ICommandHandler<ReserveSeatCommand, SeatDto> {
  private final SeatRepository seatRepository;

  public ReserveSeatCommandHandler(SeatRepository seatRepository) {
    this.seatRepository = seatRepository;
  }

  @Override
  public SeatDto handle(ReserveSeatCommand command) {
    SeatEntity existSeat = seatRepository.findSeatByFlightIdAndSeatNumberAndIsDeletedFalse(new FlightId(command.flightId()), new SeatNumber(command.seatNumber()));

    if (existSeat == null) {
         throw new SeatNumberAlreadyReservedException();
    }

    Seat seat = Mappings.toSeatAggregate(existSeat);

    seat.reserveSeat();

    SeatEntity seatEntity = Mappings.toSeatEntity(seat);
    SeatEntity seatUpdated = seatRepository.save(seatEntity);

    return Mappings.toSeatDto(seatUpdated);
  }
}
