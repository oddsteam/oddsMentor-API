package team.odds.mentor.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import team.odds.mentor.model.Expertise;
import team.odds.mentor.model.User;
import team.odds.mentor.model.UserResponse;
import team.odds.mentor.model.mapper.UserMapper;
import team.odds.mentor.repository.EndorsementRepository;
import team.odds.mentor.repository.ExpertiseRepository;
import team.odds.mentor.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock private UserRepository userRepository;
    @Mock private ExpertiseRepository expertiseRepository;
    @Mock private EndorsementRepository endorsementRepository;
    @InjectMocks private UserService userService;
    @Test
    void getUser() {
        //arrange
        String userId = "1234";
        var expertiseList = Arrays.asList(
                "java",
                "go",
                "typescript");

        var expertiseLists = new ArrayList<Expertise>();
        var expertise1 = new Expertise();
        expertise1.setId("11");
        expertise1.setSkill("java");
        expertise1.setCreatedAt(LocalDateTime.now());
        expertise1.setUpdatedAt(LocalDateTime.now());
        var expertise2 = new Expertise();
        expertise2.setId("12");
        expertise2.setSkill("go");
        expertise2.setCreatedAt(LocalDateTime.now());
        expertise2.setUpdatedAt(LocalDateTime.now());
        expertiseLists.add(expertise1);
        expertiseLists.add(expertise2);

        var user = User.builder()
                .id("1234")
                .firstNameEN("aiyaruch")
                .lastNameEN("ph")
                .firstNameTH("อัย")
                .lastNameTH("ยย")
                .nickname("ai")
                .email("aa@aa")
                .accountType("oddsMember")
                .biography("Hello World!")
                .team("molamola")
                .position("backend dev")
                .profileImageUrl("gg")
                .expertise(expertiseList)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(expertiseRepository.findExpertiseBy(user.getExpertise())).thenReturn(expertiseLists);
        when(endorsementRepository.countEndorsementByUserIdAndExpertiseId(user.getId(),expertise1.getId())).thenReturn(10);
        when(endorsementRepository.countEndorsementByUserIdAndExpertiseId(user.getId(),expertise2.getId())).thenReturn(20);
        //act
        var userResponse = userService.getUser(userId);

        //assert
        assertThat(userResponse.getId()).isEqualTo("1234");
        var userExpertiseList = Arrays.asList(
                UserResponse.Expertise.builder()
                        .id("11")
                        .skill("java")
                        .endorsed(10)
                        .build(),
                UserResponse.Expertise.builder()
                        .id("12")
                        .skill("go")
                        .endorsed(20)
                        .build()
        );
        assertThat(userResponse.getExpertise()).isEqualTo(userExpertiseList);
    }


}
