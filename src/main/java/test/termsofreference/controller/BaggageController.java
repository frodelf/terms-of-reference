package test.termsofreference.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.termsofreference.sevice.BaggageService;

@RestController
@RequestMapping("/api/baggage")
public class BaggageController {
    private final BaggageService baggageService;
    public BaggageController(BaggageService baggageService) {
        this.baggageService = baggageService;
    }

    @PutMapping("/check-in")
    public ResponseEntity<Boolean> checkIn(@RequestParam long destinationId, @RequestParam long baggageId) {
        baggageService.checkInBaggage(destinationId, baggageId);
        return ResponseEntity.ok(true);
    }
}
