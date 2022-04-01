package uz.pdp.cinemarestservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinemarestservice.model.User;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.repository.UserRepository;
import uz.pdp.cinemarestservice.service.TicketService;

import java.util.UUID;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final UserRepository userRepository;
    private final TicketService ticketService;

    @GetMapping
    public HttpEntity<?> getCurrentUserTickets() {

//=============== KEYINCHALIK @CURRENTUSER GA O'ZGARADI

        User currentUser = userRepository.findByUserName("test");
        return ticketService.getCurrentUserTickets(currentUser.getId());

//===============================
    }

    @GetMapping("/{ticketId}")
    public HttpEntity<?> transactionTicket(@PathVariable UUID ticketId) {
        ApiResponse apiResponse = ticketService.transactionTicket(ticketId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 204).body(apiResponse);
    }
}
