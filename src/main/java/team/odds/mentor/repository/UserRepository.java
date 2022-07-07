package team.odds.mentor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import team.odds.mentor.model.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Query(value = "{'_id': { $nin : [ ?0 ] }}")
    List<User> findAllExceptOne(String userId);

    Optional<String> findByEmail(String mentorId);

}
