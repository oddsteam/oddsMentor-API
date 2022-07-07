package team.odds.mentor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import team.odds.mentor.model.Booking;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {

}
