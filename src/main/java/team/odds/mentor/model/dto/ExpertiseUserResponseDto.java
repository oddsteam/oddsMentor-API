package team.odds.mentor.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ExpertiseUserResponseDto {
    private String id;
    private String skill;
    private Integer endorsed;
}
