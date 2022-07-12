package team.odds.mentor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.odds.mentor.model.Expertise;
import team.odds.mentor.model.User;
import team.odds.mentor.model.UserResponse;
import team.odds.mentor.repository.EndorsementRepository;
import team.odds.mentor.repository.ExpertiseRepository;
import team.odds.mentor.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ExpertiseRepository expertiseRepository;
    private final EndorsementRepository endorsementRepository;

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

//    public UserRequestDto addUser(UserRequestDto dataRequest) {
//        User user = userMapper.toUser(dataRequest);
//        List<ExpertiseRequestDto> expertises = dataRequest.getExpertise();
//        List<String> expertiseIdList = expertiseService.addExpertiseReturnListId(expertises);
//        user.setExpertise(expertiseIdList);
//        user.setCreatedAt(LocalDateTime.now());
//        user.setUpdatedAt(LocalDateTime.now());
//        userRepository.save(user);
//        return dataRequest;
//    }

    public UserResponse buildUserResponse(User user) {
        var expertiseList = expertiseRepository.findExpertiseByArrayId(user.getExpertise());
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
                }).toList();
    }
}
