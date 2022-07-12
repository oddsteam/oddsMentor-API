package team.odds.mentor.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import team.odds.mentor.model.Booking;
import team.odds.mentor.service.QueueProducerService;

@Component
@Aspect
@RequiredArgsConstructor
public class QueueProducerAspect {

    private final QueueProducerService queueProducerService;

    @AfterReturning(pointcut = "execution(* team.odds.mentor.service.BookingService.addBooking(..))", returning = "booking")
    public void mailToUser(Booking booking) {
        queueProducerService.sendMessage(booking.getId());
    }

}
