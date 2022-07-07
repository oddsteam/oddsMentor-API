package team.odds.mentor.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "endorsement")
public class Endorsement {
    @Id
    private String id;
    private String userId;
    private String endorsedMentorId;
    private String expertiseId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
