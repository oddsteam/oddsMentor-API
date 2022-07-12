package team.odds.mentor.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import team.odds.mentor.model.Expertise;


import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExpertiseRepository {
    private final MongoTemplate mongoTemplate;

    public List<Expertise> findExpertiseByArrayId(List<String> arrayId) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("_id").in(arrayId)
        );
        return mongoTemplate.find(query, Expertise.class);
    }

    public Expertise findExpertiseBySkill(String skill) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("skill").is(skill)
        );
        return mongoTemplate.findOne(query, Expertise.class);
    }

    public List<Expertise> findAll() {
        return mongoTemplate.findAll(Expertise.class);
    }

    public Expertise save(Expertise expertise) {
        return mongoTemplate.save(expertise);
    }
}
