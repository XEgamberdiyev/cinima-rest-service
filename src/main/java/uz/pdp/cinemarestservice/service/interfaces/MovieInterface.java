package uz.pdp.cinemarestservice.service.interfaces;

import uz.pdp.cinemarestservice.dtos.MovieDto;
import uz.pdp.cinemarestservice.poyload.ApiResponse;

import java.util.UUID;

public interface MovieInterface {

    ApiResponse getAllMovies(int page, int size, String search, String sort, boolean direction);

    ApiResponse getMovieById(UUID id);

    ApiResponse addMovie(MovieDto movieDto);

    ApiResponse editMovie(UUID id, MovieDto movieDto);

    ApiResponse deleteMovie(UUID id);
}
