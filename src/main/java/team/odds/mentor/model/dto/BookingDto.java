package team.odds.mentor.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Setter
@Getter
public class BookingDto {
    private String userId;
    private String userFullName;
    private String userEmail;
    private String mentorId;
    private String mentorFullName;
    private List<String> expertise;
    private String reason;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private String sessionDate;
    private Integer sessionDuration;
}
