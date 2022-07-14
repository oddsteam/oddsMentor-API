package team.odds.mentor.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import team.odds.mentor.model.Expertise;
import team.odds.mentor.model.ExpertiseRequest;
import team.odds.mentor.util.HelpersUtil;


import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExpertiseRepository {
    private final MongoTemplate mongoTemplate;

    public List<Expertise> findExpertiseByIdList(List<String> arrayId) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("_id").in(arrayId)
        );
        return mongoTemplate.find(query, Expertise.class);
    }

    public List<Expertise> findAll() {
        return mongoTemplate.findAll(Expertise.class, "expertise");
    }

    public Expertise save(ExpertiseRequest expertise) {
        var codeName = expertise.getSkill().replaceAll("(\\W|\\s)+", "").toLowerCase();
        Query query = new Query();
        query.addCriteria(
                Criteria.where("codeName").is(codeName)
        );
        var expertiseByCodeName = mongoTemplate.findOne(query, Expertise.class, "expertise");
        var isExisted = expertiseByCodeName != null && HelpersUtil.skillFormat(expertiseByCodeName.getSkill())
                .equals(HelpersUtil.skillFormat(expertise.getSkill()));
        return isExisted ? expertiseByCodeName :
                mongoTemplate.save(
                        Expertise.builder()
                                .skill(expertise.getSkill().trim())
                                .codeName(codeName)
                                .createdAt(LocalDateTime.now())
                                .updatedAt(LocalDateTime.now())
                                .build(), "expertise");
    }

}
