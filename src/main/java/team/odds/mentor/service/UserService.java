package team.odds.mentor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.odds.mentor.model.User;
import team.odds.mentor.model.dto.ExpertiseRequestDto;
import team.odds.mentor.model.dto.ExpertiseUserResponseDto;
import team.odds.mentor.model.dto.UserResponseDto;
import team.odds.mentor.model.dto.UserRequestDto;
import team.odds.mentor.model.mapper.UserMapper;
import team.odds.mentor.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ExpertiseService expertiseService;

    public UserResponseDto getUser(String userId) {
        var userRequest = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with this id : " + userId));
        return toUserResponse(userRequest);
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

        AtomicInteger totalEndorsement = new AtomicInteger();
        expertises.forEach((item) -> totalEndorsement.addAndGet(item.getEndorsed()));

        userResponseDto.setTotalEndorsed(totalEndorsement.get());
        return userResponseDto;
    }

}
