package team.odds.mentor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import team.odds.mentor.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
