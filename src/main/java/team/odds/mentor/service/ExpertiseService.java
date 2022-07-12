package team.odds.mentor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.odds.mentor.model.Expertise;
import team.odds.mentor.model.ExpertiseRequest;
import team.odds.mentor.repository.ExpertiseRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpertiseService {
    private final ExpertiseRepository expertiseRepository;

    public List<Expertise> getExpertiseList() {
        return expertiseRepository.findAll();
    }

    public Expertise addExpertise(ExpertiseRequest dataRequest) {
        return expertiseRepository.save(dataRequest);
    }

}
