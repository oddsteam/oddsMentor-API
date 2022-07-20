package team.odds.mentor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.odds.mentor.model.UserResponse;
import team.odds.mentor.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/odds-api/v1/mentors")
@RequiredArgsConstructor
public class MentorController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getMentorList() {
        return ResponseEntity.ok().body(userService.getUserList());
    }
    @GetMapping("/top")
    public ResponseEntity<List<UserResponse>> getMentorTopList(@RequestParam(name = "limit",required = false) Integer limit) {
        return ResponseEntity.ok().body(userService.getUserByLimit(limit));
    }
}