package team.odds.mentor.model.dto;

import lombok.*;

@ToString
@Getter
@Setter
public class EndorsementRequestDto {
    private String userId;
    private String endorsedMentorId;
    private String expertiseId;
}
