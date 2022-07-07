package team.odds.mentor.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import team.odds.mentor.model.Booking;
import team.odds.mentor.model.dto.BookingDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {
    Booking toBooking(BookingDto bookingDto);
}
