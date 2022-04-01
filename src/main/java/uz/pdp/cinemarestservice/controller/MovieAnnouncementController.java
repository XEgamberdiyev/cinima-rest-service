package uz.pdp.cinemarestservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarestservice.dtos.MovieAnnouncementDto;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.service.MovieAnnouncementService;
import uz.pdp.cinemarestservice.util.Constants;

import java.util.UUID;

@RestController
@RequestMapping("/api/movieAnnouncement")
@RequiredArgsConstructor
public class MovieAnnouncementController {

    private final MovieAnnouncementService announcementService;

    @GetMapping
    public HttpEntity<?> getAllMovieAnnouncement(@RequestParam(name = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
                                                 @RequestParam(name = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int siz){
        ApiResponse apiResponse = announcementService.getAllMovieAnnouncement(page, siz);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 204).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getMovieAnnouncement(@PathVariable UUID id){
        ApiResponse apiResponse = announcementService.getMovieAnnouncementById(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PostMapping()
    public HttpEntity<?> addMovieAnnouncement(@RequestBody MovieAnnouncementDto announcementDto){
        ApiResponse apiResponse = announcementService.addMovieAnnouncement(announcementDto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editMovieAnnouncement(@PathVariable UUID id, @RequestBody MovieAnnouncementDto announcementDto){
        ApiResponse apiResponse = announcementService.editMovieAnnouncement(id, announcementDto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteMovieAnnouncement(@PathVariable UUID id){
        ApiResponse apiResponse = announcementService.deleteMovieAnnouncement(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }
}
