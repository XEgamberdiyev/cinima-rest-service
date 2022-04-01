package uz.pdp.cinemarestservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.service.SessionService;
import uz.pdp.cinemarestservice.util.Constants;

@RestController
@RequestMapping("/api/session")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GetMapping
    public HttpEntity<?> getAllSessions(@RequestParam(name = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size,
                                        @RequestParam(name = "page", defaultValue = "1") int page,
                                        @RequestParam(name = "search", defaultValue = "") String search){
        ApiResponse apiResponse = sessionService.getAllMovieSessions(page, size, search);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 204).body(apiResponse);
    }
}
