package team.odds.mentor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.odds.mentor.model.Booking;
import team.odds.mentor.model.dto.BookingDto;
import team.odds.mentor.model.mapper.BookingMapper;
import team.odds.mentor.repository.BookingRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public Booking addBooking(BookingDto dataRequest) {
        var booking = bookingMapper.toBooking(dataRequest);
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());
        return bookingRepository.save(booking);
    }
}
