package team.odds.mentor.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "bookings")
public class Booking {
    @Id
    private String id;
    private String userId;
    private String userFullName;
    private String userEmail;
    private String mentorId;
    private String mentorFullName;
    private List<String> expertise;
    private String reason;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime sessionDate;

    private Integer sessionDuration;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updatedAt;

}
