package team.odds.mentor.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.odds.mentor.model.Booking;
import team.odds.mentor.service.BookingService;


@RestController
@RequestMapping("/odds-api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping()
    public ResponseEntity<Booking> addBooking(@RequestBody Booking dataRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.addBooking(dataRequest));
    }
}
