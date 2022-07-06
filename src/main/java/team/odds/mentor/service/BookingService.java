package team.odds.mentor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.odds.mentor.model.Booking;
import team.odds.mentor.repository.BookingRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public Booking addBooking(Booking dataRequest) {
        var booking = dataRequest;
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());
        return bookingRepository.save(booking);
    }
}
