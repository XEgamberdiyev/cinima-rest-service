package uz.pdp.cinemarestservice.projection;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.List;

//@Projection(types = Movie.class)
public interface CustomMovieById {

    Integer getId();

    String getTitle();

    Integer getCoverImgId();

    LocalDate getReleaseDate();


    // todo get more fields

    @Value("#{@genreRepository.getGenresByMovieId(target.id)}")
    List<GenreProjection> getGenres();


}
