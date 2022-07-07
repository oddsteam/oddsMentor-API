package team.odds.mentor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;
import team.odds.mentor.repository.BookingRepository;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@Service
public class QueueReceiverService {

    private final BookingRepository bookingRepository;
    private final MailSendinblueService mailSendinblueService;

    @RabbitListener(queues = "odds-mentor-message")
    public void receiveMessage(String message) throws IOException, MessagingException {
        var bookingOpt = bookingRepository.findById(message);

        if(bookingOpt.isPresent()) {
            var booking = bookingOpt.get();
            mailSendinblueService.mailToUser(booking);
        } else {
            log.warn("Booking not found : message = {}", message);
        }
    }
}
