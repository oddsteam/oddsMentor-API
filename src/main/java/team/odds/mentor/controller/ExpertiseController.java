package team.odds.mentor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.odds.mentor.model.Expertise;
import team.odds.mentor.model.dto.ExpertiseRequestDto;
import team.odds.mentor.service.ExpertiseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/odds-api/v1/expertise")
public class ExpertiseController {
    private final ExpertiseService expertiseService;

    @GetMapping()
    public ResponseEntity<List<Expertise>> getExpertiseList() {
        return  ResponseEntity.ok().body(expertiseService.getExpertiseList());
    }

    @PostMapping()
    public ResponseEntity<Expertise> addExpertise(@RequestBody ExpertiseRequestDto dataRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(expertiseService.addExpertise(dataRequest));
    }
}
