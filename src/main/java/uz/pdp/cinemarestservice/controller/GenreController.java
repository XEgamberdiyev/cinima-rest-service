package uz.pdp.cinemarestservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarestservice.model.Genre;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;
import uz.pdp.cinemarestservice.service.GenreService;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping
    public ApiResponse getAllGenres(){
        return genreService.getAllGenres();
    }

    @GetMapping("/{id}")
    public ApiResponse getGenreById(@PathVariable Integer id){
        return genreService.getGenreById(id);
    }

    @PostMapping
    public ApiResponse addGenre(@RequestBody Genre genre){
        return genreService.addGenre(genre);
    }

    @PutMapping("/{id}")
    public ApiResponse updateGenre(@PathVariable Integer id, @RequestBody Genre genre){
        return genreService.updateGenre(id, genre);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteGenre(@PathVariable Integer id){
        return genreService.deleteGenre(id);
    }


}
