package team.odds.mentor.model.dto;

import lombok.*;

@Data
public class EndorsementRequestDto {
    private String userId;
    private String endorsedMentorId;
    private String expertiseId;
}
