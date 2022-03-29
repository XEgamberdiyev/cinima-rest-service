package uz.pdp.cinemarestservice.service.interfaces;

import org.springframework.http.HttpEntity;
import uz.pdp.cinemarestservice.dto.MovieSessionDto;

public interface MovieSessionService {

    HttpEntity getAllMovieSessions(
            int page,
            int size,
            String search
    );

    HttpEntity<?> addMovieSession(MovieSessionDto movieSessionDto);


}
