package team.odds.mentor.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import team.odds.mentor.model.User;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final MongoTemplate mongoTemplate;

    public Optional<User> findById(String userId) {
        return Optional.ofNullable(mongoTemplate.findById(userId, User.class, "users"));
    }

    public List<User> findAll() {
        return mongoTemplate.findAll(User.class, "users");
    }

    public List<User> findUserByLimit(Integer limit) {
        return mongoTemplate.find(new Query().limit(limit!=null?limit:4), User.class);
    }

    public Optional<User> findByEmail(String email) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("email")
                        .is(email));
        return Optional.ofNullable(mongoTemplate.findOne(query, User.class));
    }

    public User save(User user) {
        return mongoTemplate.save(user, "users");
    }
}
