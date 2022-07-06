package team.odds.mentor.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
@Document(collection = "booking-mentor")
public class User {
    @Id
    private String id;
    private String bookingPersonFullName;
    private String bookingPersonEmail;
    private String mentorId;
    private String mentorFullName;
    private String reason;
    private Integer sessionDuration;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime sessionDate;
}
