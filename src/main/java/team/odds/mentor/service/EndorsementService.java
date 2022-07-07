package team.odds.mentor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.odds.mentor.model.Endorsement;
import team.odds.mentor.model.dto.EndorsementRequestDto;
import team.odds.mentor.model.mapper.EndorsementMapper;
import team.odds.mentor.repository.EndorsementRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EndorsementService {
    private final EndorsementMapper endorsementMapper;
    private final EndorsementRepository endorsementRepository;

    public int countEndorsementAsMentor(String userId, String expertiseId) {
        var endorsementRef = endorsementRepository.findEndorsementBy(userId, expertiseId);
        return endorsementRef.size();
    }

    public Endorsement addEndorsement(EndorsementRequestDto dataRequest) {
        var endorsement = endorsementMapper.toEndorsement(dataRequest);
        endorsement.setCreatedAt(LocalDateTime.now());
        endorsement.setUpdatedAt(LocalDateTime.now());
        return endorsementRepository.save(endorsement);
    }
}
