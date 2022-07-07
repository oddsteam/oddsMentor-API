package team.odds.mentor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import team.odds.mentor.repository.BookingRepository;
import team.odds.mentor.repository.UserRepository;


@Service
@RequiredArgsConstructor
@Slf4j
public class QueueReceiverService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final MailSendinblueService mailSendinblueService;

    @RabbitListener(queues = "odds-mentor-message")
    public void receiveMessage(String message) {
        var bookingOpt = bookingRepository.findById(message);
        if (bookingOpt.isPresent()) {
            var booking = bookingOpt.get();
            var mentorEmail = userRepository.findByEmail(booking.getMentorId());
            var isSuccess = mailSendinblueService.mailToUser(booking, mentorEmail);
            log.info("isSuccess : {}", isSuccess);
        } else {
            log.warn("Booking not found : message = {}", message);
        }
    }
}
