package uz.pdp.cinemarestservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarestservice.dtos.TicketDto;
import uz.pdp.cinemarestservice.model.User;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.repository.UserRepository;
import uz.pdp.cinemarestservice.service.CartService;

import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    private final UserRepository userRepository;

    @GetMapping("/{userId}")
    public HttpEntity<?> showTicketsInTheCart(@PathVariable UUID userId) {
        ApiResponse apiResponse = cartService.getTicketInTheCart(userId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 204).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addTicketInToCart(@RequestBody TicketDto ticketDto) {

//=============== KEYINCHALIK @CURRENTUSER GA O'ZGARADI

        User currentUser = userRepository.findByUserName("test");

//===============================


        ApiResponse apiResponse = cartService.addToCart(currentUser.getId(), ticketDto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 204).body(apiResponse);
    }

    @DeleteMapping("/{userId}/{ticketId}")
    public HttpEntity<?> deleteTicketFromCart(@PathVariable UUID userId, @PathVariable UUID ticketId) {
        ApiResponse apiResponse = cartService.deleteTicketFromCart(userId, ticketId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 204).body(apiResponse);
    }

    @DeleteMapping("/clear/{userId}")
    public HttpEntity<?> clearCart(@PathVariable UUID userId) {
        ApiResponse apiResponse = cartService.clearCart(userId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 204).body(apiResponse);
    }
}
