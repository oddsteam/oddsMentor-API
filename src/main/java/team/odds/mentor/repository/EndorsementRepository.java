package team.odds.mentor.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import team.odds.mentor.model.Endorsement;

@Repository
@RequiredArgsConstructor
public class EndorsementRepository {

    private final MongoTemplate mongoTemplate;

    public int countEndorsementByUserIdAndExpertiseId(String userId, String expertiseId) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("endorsedMentorId")
                        .is(userId)
                        .and("expertiseId")
                        .is(expertiseId)
        );
        var countEndorsement = mongoTemplate.count(query, "endorsement");
        return Math.toIntExact(countEndorsement);
    }

    public Endorsement saveEndorsement(Endorsement endorsement) {
        return mongoTemplate.save(endorsement, "endorsement");
    }
    
}
