package uz.pdp.cinemarestservice.projection;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public interface CustomTicket {

    UUID getId();

    Integer getSeatNumber();

    Integer getRowNumber();

    String getHallName();

    Double getPrice();

    UUID getMovieId();

    String getMovieName();

    LocalDate getStartDate();

    LocalTime getStartTime();
}
