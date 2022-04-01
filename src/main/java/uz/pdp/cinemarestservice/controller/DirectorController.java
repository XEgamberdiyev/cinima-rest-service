package uz.pdp.cinemarestservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemarestservice.model.Director;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.service.DirectorService;

import java.util.UUID;

@RestController
@RequestMapping("/api/director")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping
    public HttpEntity<?> getAllDirectors() {
        ApiResponse apiResponse = directorService.getAllDirectors();
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 204).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getDirectorById(@PathVariable UUID id) {
        ApiResponse apiResponse = directorService.getDirectorById(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addDirector(@RequestPart("file") MultipartFile file, @RequestPart("json-director") Director director) {
        ApiResponse apiResponse = directorService.addDirector(file, director);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 209).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editDirector(@PathVariable UUID id, @RequestPart("file") MultipartFile file, @RequestPart("json-director") Director director) {
        ApiResponse apiResponse = directorService.editDirector(id, file, director);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 209).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteDirector(@PathVariable UUID id) {
        ApiResponse apiResponse = directorService.deleteDirector(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 209).body(apiResponse);
    }
}
