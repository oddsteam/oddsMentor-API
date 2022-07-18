package team.odds.mentor.controller;

import com.mongodb.client.result.DeleteResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.odds.mentor.model.Expertise;
import team.odds.mentor.model.ExpertiseRequest;
import team.odds.mentor.service.ExpertiseService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/odds-api/v1/expertise")
@RequiredArgsConstructor
public class ExpertiseController {
    private final ExpertiseService expertiseService;

    @GetMapping()
    public ResponseEntity<List<Expertise>> getExpertiseList() {
        return  ResponseEntity.ok().body(expertiseService.getExpertiseList());
    }

    @PostMapping()
    public ResponseEntity<Expertise> addExpertise(@RequestBody ExpertiseRequest dataRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(expertiseService.addExpertise(dataRequest));
    }

    @DeleteMapping("/{expertiseId}")
    public ResponseEntity<Map<String, String>> removeExpertise(@PathVariable("expertiseId")String expertiseId) {
        return ResponseEntity.status(HttpStatus.OK).body(expertiseService.removeExpertise(expertiseId));
    }
}
