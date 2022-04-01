package uz.pdp.cinemarestservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarestservice.model.Genre;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.service.GenreService;

import java.util.UUID;

@RestController
@RequestMapping("/api/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public HttpEntity<?> getAllGenres(){
        ApiResponse apiResponse = genreService.getAllGenre();
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 204).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getGenreById(@PathVariable UUID id){
        ApiResponse apiResponse = genreService.getGenreById(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addGenre(@RequestBody Genre genre){
        ApiResponse apiResponse = genreService.addGenre(genre);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editGenre(@PathVariable UUID id,@RequestBody Genre genre){
        ApiResponse apiResponse = genreService.editGenre(id, genre);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteGenre(@PathVariable UUID id){
        ApiResponse apiResponse = genreService.deleteGenre(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 :409).body(apiResponse);
    }
}
