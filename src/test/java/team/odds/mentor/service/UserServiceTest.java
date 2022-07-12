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
        var expertiseIdList = userExpertiseList.stream().map(UserResponse.Expertise::getId).toList();
        var expertiseList = userExpertiseList.stream().map(item->Expertise.builder()
                .id(item.getId())
                .skill(item.getSkill())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build()
        ).toList();
        String userId = "1234";
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
                .expertise(expertiseIdList)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(expertiseRepository.findExpertiseBy(user.getExpertise())).thenReturn(expertiseList);
        when(endorsementRepository.countEndorsementByUserIdAndExpertiseId(user.getId(),"11")).thenReturn(10);
        when(endorsementRepository.countEndorsementByUserIdAndExpertiseId(user.getId(),"12")).thenReturn(20);
        //act
        var userResponse = userService.getUser(userId);
        //assert
        assertThat(userResponse.getId()).isEqualTo("1234");
        assertThat(userResponse.getExpertise()).isEqualTo(userExpertiseList);
        assertThat(userResponse.getTotalEndorsed()).isEqualTo(30);
    }


}
