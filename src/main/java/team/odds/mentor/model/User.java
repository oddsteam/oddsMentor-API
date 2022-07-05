package team.odds.mentor.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class User {
    @Id
    private String userId;
    private String firstNameEN;
    private String lastNameEN;
}
