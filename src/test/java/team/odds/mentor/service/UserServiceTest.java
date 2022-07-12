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
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock private UserRepository userRepository;
    @Mock private ExpertiseRepository expertiseRepository;
    @Mock private EndorsementRepository endorsementRepository;
    @InjectMocks private UserService userService;
    List<UserResponse.Expertise> userExpertiseList = Arrays.asList(
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
    List<String> expertiseIdList = userExpertiseList.stream().map(UserResponse.Expertise::getId).toList();
    List<Expertise> expertiseList = userExpertiseList.stream().map(item->Expertise.builder()
            .id(item.getId())
            .skill(item.getSkill())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build()
    ).toList();
    User user = User.builder()
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
    UserResponse userResponse = UserResponse.builder()
            .id(user.getId())
            .fullNameEN(user.getFirstNameEN() + " " + user.getLastNameEN())
            .fullNameTH(user.getFirstNameTH() + " " + user.getLastNameTH())
            .nickname(user.getNickname())
            .type(user.getAccountType())
            .biography(user.getBiography())
            .team(user.getTeam())
            .position(user.getPosition())
            .profileImageUrl(user.getProfileImageUrl())
            .totalEndorsed(30)
            .expertise(userExpertiseList)
            .build();
    @Test
    void getUser_shouldReturnUserResponse() {
        // Arrange
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(expertiseRepository.findExpertiseBy(user.getExpertise())).thenReturn(expertiseList);
        when(endorsementRepository.countEndorsementByUserIdAndExpertiseId(user.getId(),"11")).thenReturn(10);
        when(endorsementRepository.countEndorsementByUserIdAndExpertiseId(user.getId(),"12")).thenReturn(20);
        // Act
        var userResponse = userService.getUser(user.getId());
        // Assert
        assertThat(userResponse.getId()).isEqualTo("1234");
        assertThat(userResponse.getExpertise()).isEqualTo(userExpertiseList);
        assertThat(userResponse.getTotalEndorsed()).isEqualTo(30);
    }

//    @Test
//    void buildUserResponse_shouldReturnUserResponse() {
//         Arrange
//        when(userService.buildUserResponse(user)).thenReturn(userResponse);
//        when(expertiseRepository.findExpertiseBy(user.getExpertise())).thenReturn(expertiseList);
//        when(userService.buildUserExpertise(user.getId(),expertiseList)).thenReturn(userExpertiseList);
//         Act
//        var userRes = userService.buildUserResponse(user);
//        System.out.println(userRes.getExpertise());
//         Assert
//        assertThat(userRes.getId()).isEqualTo("1234");
//    }
}
