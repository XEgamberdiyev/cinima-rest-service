package uz.pdp.cinemarestservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemarestservice.dto.MovieDto;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;
import uz.pdp.cinemarestservice.service.MovieServiceImpl;
import uz.pdp.cinemarestservice.util.Constant;

import java.io.IOException;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    MovieServiceImpl movieService;

//    @GetMapping
//    public HttpEntity<?> getAllMovies() {
//        ApiResponse movies = movieService.getAllMovies();
//        return ResponseEntity.status(movies.isSuccess() ? 200 : 404).body(movies);
//    }


    @GetMapping
    public HttpEntity getAllMovies(
            @RequestParam(name = "size", defaultValue = Constant.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "sort", defaultValue = "title") String sort
    ) {
        return movieService.getAllMovies(page, size, search, sort, true);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getMovieById(@PathVariable Integer id) {
        ApiResponse movieById = movieService.getMovieById(id);
        return ResponseEntity.status(movieById.isSuccess() ? 200 : 404).body(movieById);
    }

    @PostMapping
    public HttpEntity<?> addMovie(@RequestPart(name = "json") MovieDto movieDto, Integer id,
                                  @RequestPart(name = "file") MultipartFile request) throws IOException {
        ApiResponse apiResponse = movieService.addMovie(movieDto, id, request);
        return ResponseEntity.status(apiResponse.isSuccess()? 200: 404).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateMovie(@PathVariable Integer id,
                                     @RequestPart(name = "json") MovieDto movieDto,
                                     MultipartFile request) throws IOException {
        ApiResponse response = movieService.addMovie(movieDto, id, request);
        return ResponseEntity.status(response.isSuccess()? 200: 404).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteMovie(@PathVariable Integer id){
        ApiResponse response = movieService.deleteMovie(id);
        return ResponseEntity.status(response.isSuccess()? 200: 404).body(response);
    }

}
