package team.odds.mentor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.odds.mentor.model.Endorsement;
import team.odds.mentor.model.dto.EndorsementRequestDto;
import team.odds.mentor.service.EndorsementService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/odds-api/v1/endorsement")

public class EndorsementController {

    private final EndorsementService endorsementService;

    @PostMapping()
    public ResponseEntity<Endorsement> addEndorsement(@RequestBody EndorsementRequestDto dataRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(endorsementService.addEndorsement(dataRequest));
    }
}
