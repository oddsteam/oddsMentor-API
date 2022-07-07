package team.odds.mentor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import team.odds.mentor.model.Endorsement;

import java.util.List;

@Repository
public interface EndorsementRepository extends MongoRepository<Endorsement, String> {

    @Query(value = "{'endorsedMentorId' :  ?0 , 'expertiseId' :  ?1}")
    List<Endorsement> findEndorsementBy(String userId, String expertiseId);
}
