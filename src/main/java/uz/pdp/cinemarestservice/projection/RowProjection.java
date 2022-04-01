package uz.pdp.cinemarestservice.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.cinemarestservice.model.Hall;
import uz.pdp.cinemarestservice.model.Row;

@Projection(types = {Row.class, Hall.class})
public interface RowProjection {

    Long getId();

    Integer getNumber();

    String getName();
}
