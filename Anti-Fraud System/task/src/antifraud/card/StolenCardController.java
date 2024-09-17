package antifraud.card;


import antifraud.card.dto.RegisterStolenCardRequest;
import antifraud.card.dto.StolenCardResponse;
import antifraud.card.dto.StolenCardDeleteResponse;
import antifraud.card.validation.ValidCardNumber;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/antifraud/stolencard")
@RequiredArgsConstructor
@Validated
public class StolenCardController {

    private final StolenCardService stolenCardService;

    @PostMapping
    ResponseEntity<StolenCardResponse> registerStolenCard(@RequestBody @Valid RegisterStolenCardRequest request) {
        var response = stolenCardService.registerStolenCard(request);
        return ResponseEntity.ok().body(response);
    }


    @DeleteMapping("/{number}")
    ResponseEntity<StolenCardDeleteResponse> deleteStolenCard(@Valid @PathVariable @ValidCardNumber String number) {
        var response = stolenCardService.deleteStolenCard(number);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping()
    public ResponseEntity<List<StolenCardResponse>> getStolenCards() {
        var response = stolenCardService.getStolenCards();
        return ResponseEntity.ok().body(response);
    }


}
