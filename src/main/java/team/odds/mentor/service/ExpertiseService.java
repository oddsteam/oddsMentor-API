package team.odds.mentor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.odds.mentor.model.Expertise;
import team.odds.mentor.model.dto.ExpertiseRequestDto;
import team.odds.mentor.model.mapper.ExpertiseMapper;
import team.odds.mentor.repository.ExpertiseRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpertiseService {
    private final ExpertiseRepository expertiseRepository;
    private final ExpertiseMapper expertiseMapper;

    public List<Expertise> getExpertiseList() {
        return expertiseRepository.findAll();
    }

    public Expertise addExpertise(ExpertiseRequestDto dataRequest) {
        var expertise = expertiseMapper.toExpertise(dataRequest);
        expertise.setCreatedAt(LocalDateTime.now());
        expertise.setUpdatedAt(LocalDateTime.now());
        return expertiseRepository.save(expertise);
    }

    public List<String> addExpertiseReturnListId(List<ExpertiseRequestDto> expertiseList) {
        List<String> expertiseIdList = new ArrayList<>();
        expertiseList.forEach((expertise) -> {
            String expertiseId = expertise.getId();
            if(expertiseId == null) {
                var newExp = addExpertise(expertise);
                expertiseId = newExp.getId();
            }
            expertiseIdList.add(expertiseId);
        });
        return expertiseIdList;
    }
}
