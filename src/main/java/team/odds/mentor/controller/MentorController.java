package team.odds.mentor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.odds.mentor.model.UserResponse;
import team.odds.mentor.model.dto.UserResponseDto;
import team.odds.mentor.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/odds-api/v1/mentors")
public class MentorController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getMentorList() {
        return ResponseEntity.ok().body(userService.getUserList());
    }
}
