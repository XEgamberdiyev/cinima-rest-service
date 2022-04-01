package uz.pdp.cinemarestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.cinemarestservice.model.Genre;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.repository.GenreRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public ApiResponse getAllGenre(){
        List<Genre> genreList = genreRepository.findAll();
        if (genreList.size() == 0){
            return new ApiResponse("Genre not found!!!", false);
        }
        return new ApiResponse("Success!", true, genreList);
    }


    public ApiResponse getGenreById(UUID id){
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (!optionalGenre.isPresent()) {
            return new ApiResponse("Genre not found!!!", false);
        }
        Genre genre = optionalGenre.get();
        return new ApiResponse("Success!",true, genre);
    }


    public ApiResponse addGenre(Genre genre){
        try {
            Genre saveGenre = genreRepository.save(genre);
            return new ApiResponse("Success!",true, saveGenre);
        } catch (Exception e){
            return new ApiResponse("Error!!!", false);
        }
    }


    public ApiResponse editGenre(UUID id, Genre genre){
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (!optionalGenre.isPresent()) {
            return new ApiResponse("Genre not found!!!", false);
        }
        try {
            Genre editGenre = optionalGenre.get();
            editGenre.setName(genre.getName());
            Genre newGenre = genreRepository.save(editGenre);
            return new ApiResponse("Success!",true, newGenre);
        } catch (Exception e){
            return new ApiResponse("Error!!!", false);
        }
    }


    public ApiResponse deleteGenre(UUID id){
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (!optionalGenre.isPresent()) {
            return new ApiResponse("Genre not found!!!", false);
        }
        try {
            genreRepository.deleteById(id);
            return new ApiResponse("Success!", true);
        } catch (Exception e){
            return new ApiResponse("Error!!!", false);
        }
    }
}
