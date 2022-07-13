package team.odds.mentor.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import team.odds.mentor.model.Expertise;
import team.odds.mentor.model.User;
import team.odds.mentor.model.UserResponse;
import team.odds.mentor.repository.EndorsementRepository;
import team.odds.mentor.repository.ExpertiseRepository;
import team.odds.mentor.repository.UserRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private ExpertiseRepository expertiseRepository;
    @Mock
    private EndorsementRepository endorsementRepository;
    @InjectMocks
    private UserService userService;

    @Test
    void getUser_shouldReturnUserResponseWhenIdExisted() {
        // Arrange
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
        var expertiseList = userExpertiseList.stream().map(item -> Expertise.builder()
                .id(item.getId())
                .skill(item.getSkill())
                .build()
        ).toList();
        var user = User.builder()
                .id("1234")
                .expertise(expertiseIdList)
                .build();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(expertiseRepository.findExpertiseByIdList(user.getExpertise())).thenReturn(expertiseList);
        when(endorsementRepository.countEndorsementByUserIdAndExpertiseId(user.getId(), userExpertiseList.get(0).getId()))
                .thenReturn(userExpertiseList.get(0).getEndorsed());
        when(endorsementRepository.countEndorsementByUserIdAndExpertiseId(user.getId(), userExpertiseList.get(1).getId()))
                .thenReturn(userExpertiseList.get(1).getEndorsed());
        // Act
        var userResponse = userService.getUser(user.getId());
        // Assert
        assertThat(userResponse.getId()).isEqualTo("1234");
        assertThat(userResponse.getExpertise()).isEqualTo(userExpertiseList);
        assertThat(userResponse.getTotalEndorsed()).isEqualTo(30);
    }

    @Test
    void getUser_ShouldReturnErrorWhenIdNotFound() {
        // Arrange
        var error = new RuntimeException("User not found with this id " + "");
        doThrow(error).when(userRepository).findById(any());
        try {
            // Act
            userService.getUser("");
        } catch (RuntimeException exception) {
            // Assert
            assertThat(exception.getMessage()).isEqualTo(error.getMessage());
        }
    }

    @Test
    void getUserList_ShouldReturnUserResponseList() {
        // Arrange
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
        var users = Arrays.asList(
                User.builder()
                        .id("1234")
                        .expertise(expertiseIdList)
                        .build(),
                User.builder()
                        .id("4567")
                        .expertise(expertiseIdList)
                        .build()
        );
        var expertiseList = userExpertiseList.stream().map(item -> Expertise.builder()
                .id(item.getId())
                .skill(item.getSkill())
                .build()
        ).toList();
        when(userRepository.findAll()).thenReturn(users);
        when(expertiseRepository.findExpertiseByIdList(users.get(0).getExpertise())).thenReturn(expertiseList);
        when(endorsementRepository.countEndorsementByUserIdAndExpertiseId
                (users.get(0).getId(), userExpertiseList.get(0).getId()))
                .thenReturn(userExpertiseList.get(0).getEndorsed());
        when(endorsementRepository.countEndorsementByUserIdAndExpertiseId
                (users.get(0).getId(), userExpertiseList.get(1).getId()))
                .thenReturn(userExpertiseList.get(1).getEndorsed());
        // Act
        var userResponse = userService.getUserList();
        // Assert
        assertThat(userResponse.get(0).getId()).isEqualTo("1234");
        assertThat(userResponse.get(1).getId()).isEqualTo("4567");
        assertThat(userResponse.get(0).getExpertise()).isEqualTo(userExpertiseList);
        assertThat(userResponse.get(0).getTotalEndorsed()).isEqualTo(30);
    }
}