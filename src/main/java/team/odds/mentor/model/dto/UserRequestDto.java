package team.odds.mentor.model.dto;

import lombok.*;

import java.util.List;

@Data
public class UserRequestDto {
    private String firstNameEN;
    private String firstNameTH;
    private String lastNameEN;
    private String lastNameTH;
    private String nickname;
    private String email;
    private String accountType;
    private String biography;
    private String team;
    private String position;
    private String profileImageUrl;
    private List<ExpertiseRequestDto> expertise;
}
