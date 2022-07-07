package team.odds.mentor.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "users")
public class User {
    @Id
    private String id;
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
    private List<String> expertise;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

