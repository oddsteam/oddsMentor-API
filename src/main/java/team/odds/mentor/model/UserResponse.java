package team.odds.mentor.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class UserResponse {
    private String id;
    private String fullNameEN;
    private String fullNameTH;
    private String nickname;
    private String type;
    private String biography;
    private String team;
    private String position;
    private String profileImageUrl;
    private Integer totalEndorsed;
    private List<Expertise> expertise;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Data
    @Builder
    public static class Expertise {
        private String id;
        private String skill;
        private Integer endorsed;
    }
}
