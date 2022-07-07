package team.odds.mentor.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
public class UserResponseDto {

    @Id
    private String id;
    private String fullNameEN;
    private String fullNameTH;
    private String nickname;
    private String accountType;
    private String biography;
    private String team;
    private String position;
    private String profileImageUrl;
    private int totalEndorsed;
    private List<ExpertiseUserResponseDto> expertise = List.of();
}
