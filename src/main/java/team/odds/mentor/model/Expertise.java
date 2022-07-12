package team.odds.mentor.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "expertise")
@Data
@Builder
public class Expertise {
    @Id
    private String id;
    private String skill;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
