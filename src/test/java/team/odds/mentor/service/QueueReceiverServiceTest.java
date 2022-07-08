package team.odds.mentor.service;

import org.junit.jupiter.api.Test;
import team.odds.mentor.model.Booking;
import team.odds.mentor.repository.BookingRepository;
import team.odds.mentor.repository.UserRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class QueueReceiverServiceTest {
    @Test
    void ifBookingIsEmptyShouldNotSendMail() {
        // arrange
        BookingRepository bookingRepository = mock(BookingRepository.class);
        UserRepository userRepository = null;
        MailSendinblueService mailSendinblueService = mock(MailSendinblueService.class);
        var queueReceiverService = new QueueReceiverService(bookingRepository, userRepository, mailSendinblueService);
        var message = "";

        // act
        queueReceiverService.receiveMessage(message);

        // asserts
        verify(mailSendinblueService, never()).mailToUser(any(), any());
    }
}
