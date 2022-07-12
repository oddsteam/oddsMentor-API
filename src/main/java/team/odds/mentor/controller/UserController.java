package team.odds.mentor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.odds.mentor.model.UserResponse;
import team.odds.mentor.model.dto.UserRequestDto;
import team.odds.mentor.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/odds-api/v1/users")
public class UserController {
    private final UserService userService;

//    @PostMapping()
//    public ResponseEntity<UserRequestDto> addUser(@RequestBody UserRequestDto dataRequest) {
//        return ResponseEntity.ok().body(userService.addUser(dataRequest));
//    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("userId") String userId) {
        return ResponseEntity.ok().body(userService.getUser(userId));
    }
}
