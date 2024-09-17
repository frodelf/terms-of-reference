package test.termsofreference.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.termsofreference.sevice.TicketService;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/check-availability/{id}")
    public ResponseEntity<Boolean> checkTicketAvailability(@PathVariable int id) {
        return ResponseEntity.ok(ticketService.isTicketAvailable(id));
    }
}