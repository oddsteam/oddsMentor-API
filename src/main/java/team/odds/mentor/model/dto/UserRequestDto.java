package team.odds.mentor.model.dto;

import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
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
    private List<Expertise> expertise;

    @ToString
    @Getter
    @Setter
    public static class Expertise {
        private String id;
        private String skill;
    }
}
