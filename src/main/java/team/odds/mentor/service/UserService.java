package team.odds.mentor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.odds.mentor.model.Expertise;
import team.odds.mentor.model.User;
import team.odds.mentor.model.UserResponse;
import team.odds.mentor.model.ExpertiseRequest;
import team.odds.mentor.model.dto.UserRequestDto;
import team.odds.mentor.model.mapper.UserMapper;
import team.odds.mentor.repository.EndorsementRepository;
import team.odds.mentor.repository.ExpertiseRepository;
import team.odds.mentor.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ExpertiseRepository expertiseRepository;
    private final EndorsementRepository endorsementRepository;
    private final UserMapper userMapper;
    private final ExpertiseService expertiseService;

    public UserResponse getUser(String userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with this id : " + userId));
        return buildUserResponse(user);
    }

    public List<UserResponse> getUserList() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::buildUserResponse)
                .toList();
    }

    public UserResponse addUser(UserRequestDto dataRequest) {
        var userByEmail = userRepository.findByEmail(dataRequest.getEmail());
        if (userByEmail.isPresent())
            throw new RuntimeException("duplicate email");

        var user = userMapper.toUser(dataRequest);
        user.setExpertise(getExpertiseIdList(dataRequest.getExpertise()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        var userRes = userRepository.save(user);
        return buildUserResponse(userRes);
    }

    public UserResponse updateUser(String userId, UserRequestDto dataRequest) {
        var userInfo = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with this id : " + userId));
        var user = userMapper.toUser(dataRequest);
        user.setId(userInfo.getId());
        user.setExpertise(getExpertiseIdList(dataRequest.getExpertise()));
        user.setCreatedAt(userInfo.getCreatedAt());
        user.setUpdatedAt(LocalDateTime.now());
        var userRes = userRepository.save(user);
        return buildUserResponse(userRes);
    }

    public UserResponse buildUserResponse(User user) {
        var expertiseList = expertiseRepository.findExpertiseByIdList(user.getExpertise());
        var userExpertise = buildUserExpertise(user.getId(), expertiseList);
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
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public List<UserResponse.Expertise> buildUserExpertise(String userId, List<Expertise> expertiseList) {
        return expertiseList.stream()
                .map(item -> {
                    int endorsement = endorsementRepository.countEndorsementByUserIdAndExpertiseId(userId, item.getId());
                    return UserResponse.Expertise.builder()
                            .id(item.getId())
                            .skill(item.getSkill())
                            .endorsed(endorsement)
                            .build();
                }).collect(Collectors.toList());
    }

    public List<String> getExpertiseIdList(List<UserRequestDto.Expertise> expertiseList) {
        return expertiseList.stream()
                .map(value -> {
                    if (value.getId() == null) {
                        var newExpertise = expertiseService
                                .addExpertise(new ExpertiseRequest(value.getSkill()));
                        return newExpertise.getId();
                    }
                    return value.getId();
                }).toList();
    }
}
