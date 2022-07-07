package team.odds.mentor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import team.odds.mentor.model.Expertise;

import java.util.List;

@Repository
public interface ExpertiseRepository extends MongoRepository<Expertise, String> {
    @Query(value = "{ '_id' : {'$in' : ?0 } }")
    List<Expertise> findExpertiseBy(List<String> arrayId);

    @Query(value = "{'skill' :  ?0 }")
    Expertise findExpertiseBySkill(String skill);

}
