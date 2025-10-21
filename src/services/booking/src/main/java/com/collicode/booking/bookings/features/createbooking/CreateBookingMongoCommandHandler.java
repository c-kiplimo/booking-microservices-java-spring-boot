package com.collicode.booking.bookings.features.createbooking;


import com.collicode.booking.bookings.exceptions.BookingAlreadyExistException;
import com.collicode.booking.bookings.features.Mappings;
import com.collicode.booking.data.mongo.documents.BookingDocument;
import com.collicode.booking.data.mongo.repositories.BookingReadRepository;
import com.collicode.buildingblocks.mediator.abstractions.commands.ICommandHandler;
import com.collicode.buildingblocks.mediator.abstractions.requests.Unit;
import org.springframework.stereotype.Service;

@Service
public class CreateBookingMongoCommandHandler implements ICommandHandler<CreateBookingMongoCommand, Unit> {
    private final BookingReadRepository bookingReadRepository;

    public CreateBookingMongoCommandHandler(BookingReadRepository bookingReadRepository) {
        this.bookingReadRepository = bookingReadRepository;
    }

    @Override
    public Unit handle(CreateBookingMongoCommand command) {

        BookingDocument existBooking = bookingReadRepository.findBookingByBookingIdAndIsDeletedFalse(command.id());
        if (existBooking != null) {
            throw new BookingAlreadyExistException();
        }

        BookingDocument bookingDocument = Mappings.toBookingDocument(command);

        bookingReadRepository.save(bookingDocument);

        return Unit.VALUE;
    }
}
