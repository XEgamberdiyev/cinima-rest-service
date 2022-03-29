package uz.pdp.cinemarestservice.service.interfaces;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemarestservice.dto.MovieDto;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;

import java.io.IOException;

@Service
public interface MovieService {

    public ApiResponse getAllMovies();

    public ApiResponse getMovieById(Integer id);

    public ApiResponse addMovie(MovieDto movieDto, Integer id ,
                                MultipartFile request) throws IOException;

    public ApiResponse updateMovie(Integer id, MovieDto movieDto);

    public ApiResponse deleteMovie(Integer id);

    HttpEntity getAllMovies(
            int page,
            int size,
            String search,
            String sort,
            boolean direction
    );


}
