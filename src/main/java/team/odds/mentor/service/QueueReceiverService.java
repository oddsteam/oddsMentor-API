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
        if (bookingOpt.isEmpty()) {
            log.warn("Booking not found : message = {}", message);
            return;
        }
        var booking = bookingOpt.get();
        var mentor = userRepository.findById(booking.getMentorId());
        if (mentor.isEmpty()) {
            log.warn("failed by mentorId: {}", booking.getMentorId());
            return;
        }
        var isFail = mailSendinblueService.mailToUser(booking, mentor.get().getEmail());
        log.info("sending email {} with failed status {}", mentor.get().getEmail(), isFail);
    }

}

