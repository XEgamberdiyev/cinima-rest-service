package uz.pdp.cinemarestservice.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.cinemarestservice.model.Movie;

import java.util.Date;
import java.util.UUID;

@Projection(types = {Movie.class})
public interface CustomMovie {

    UUID getId();

    String getTitle();

    Date getReleaseDate();

    UUID coverImgId();

}
