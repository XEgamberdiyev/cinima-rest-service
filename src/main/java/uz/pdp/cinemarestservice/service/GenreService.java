package uz.pdp.cinemarestservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarestservice.model.Genre;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;
import uz.pdp.cinemarestservice.repository.GenreRepo;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    GenreRepo genreRepo;

    public ApiResponse getAllGenres() {
        List<Genre> all = genreRepo.findAll();
        if (all != null) {
            return new ApiResponse("Success", true, all);
        }
        return new ApiResponse("Not found", false, null);
    }

    public ApiResponse getGenreById(Integer id) {
        Optional<Genre> optionalGenre = genreRepo.findById(id);
        if (optionalGenre.isPresent()) {
            Genre genre = optionalGenre.get();
            return new ApiResponse("Success", true, genre);
        }
        return new ApiResponse("Not found", false, null);
    }

    public ApiResponse addGenre(Genre genre) {
        Genre genre1 = new Genre();
        genre1.setName(genre.getName());
        Genre save = genreRepo.save(genre1);
        if (genre1 != null) {
            return new ApiResponse("Success", true, save);
        }
        return new ApiResponse("Not found", false, null);
    }

    public ApiResponse deleteGenre(Integer id){
        Optional<Genre> optionalGenre = genreRepo.findById(id);
        if (optionalGenre.isPresent()) {
            Genre genre = optionalGenre.get();
            genreRepo.delete(genre);
            return new ApiResponse("Success", true);
        }
        return new ApiResponse("Not found", false);
    }

    public ApiResponse updateGenre(Integer id, Genre genre){
        Optional<Genre> optionalGenre = genreRepo.findById(id);
        if (optionalGenre.isPresent()) {
            Genre genre1 = optionalGenre.get();
            genre1.setName(genre.getName());
            Genre save = genreRepo.save(genre1);
            return new ApiResponse("Success", true, save);
        }
        return new ApiResponse("Not found", false, null);
    }

}