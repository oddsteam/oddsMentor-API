package team.odds.mentor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.odds.mentor.model.Expertise;
import team.odds.mentor.model.User;
import team.odds.mentor.model.UserResponse;
import team.odds.mentor.model.dto.ExpertiseRequestDto;
import team.odds.mentor.model.dto.ExpertiseUserResponseDto;
import team.odds.mentor.model.dto.UserResponseDto;
import team.odds.mentor.model.dto.UserRequestDto;
import team.odds.mentor.model.mapper.UserMapper;
import team.odds.mentor.repository.ExpertiseRepository;
import team.odds.mentor.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ExpertiseRepository expertiseRepository;
    private final UserMapper userMapper;
    private final ExpertiseService expertiseService;
    private final EndorsementService endorsementService;

    public UserResponse getUser(String userId) {
        var userRequest = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with this id : " + userId));
        return buildUserResponse(userRequest);
    }

    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();

        users.forEach((user) -> {
            UserResponseDto userResponseDto = toUserResponse(user);
            userResponseDtoList.add(userResponseDto);
        });

        return userResponseDtoList;
    }

    public UserRequestDto addUser(UserRequestDto dataRequest) {
        User user = userMapper.toUser(dataRequest);
        List<ExpertiseRequestDto> expertises = dataRequest.getExpertise();
        List<String> expertiseIdList = expertiseService.addExpertiseReturnListId(expertises);
        user.setExpertise(expertiseIdList);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return dataRequest;
    }

    public UserResponseDto toUserResponse(User user) {
        UserResponseDto userResponseDto = userMapper.toUserResponse(user);
        List<ExpertiseUserResponseDto> expertises = expertiseService.getUserExpertise(user.getId(), user.getExpertise());
        userResponseDto.setExpertise(expertises);

        int totalEndorsement = expertises.stream().mapToInt(ExpertiseUserResponseDto::getEndorsed).sum();

        userResponseDto.setTotalEndorsed(totalEndorsement);
        return userResponseDto;
    }

    public UserResponse buildUserResponse(User user) {
        var expertiseListRes = expertiseRepository.findExpertiseBy(user.getExpertise());
        var userExpertise = buildUserExpertise(user.getId(), expertiseListRes);
        int totalEndorsement = userExpertise.stream().mapToInt(UserResponse.Expertise::getEndorsed).sum();
        return UserResponse.builder()
                .id(user.getId())
                .fullNameEN(user.getFirstNameEN() + " " + user.getLastNameEN())
                .fullNameTH(user.getFirstNameTH() + " " + user.getLastNameTH())
                .nickname(user.getNickname())
                .type(user.getAccountType())
                .biography(user.getBiography())
                .team(user.getTeam())
                .position(user.getPosition())
                .profileImageUrl(user.getProfileImageUrl())
                .totalEndorsed(totalEndorsement)
                .expertise(userExpertise)
                .build();
    }

    public List<UserResponse.Expertise> buildUserExpertise(String userId, List<Expertise> expertiseList) {
        return expertiseList.stream()
                .map(item -> {
                    int endorsement = endorsementService.countEndorsementAsMentor(userId, item.getId());
                    return UserResponse.Expertise.builder()
                            .id(item.getId())
                            .skill(item.getSkill())
                            .endorsed(endorsement)
                            .build();
                }).collect(Collectors.toList());
    }
}
