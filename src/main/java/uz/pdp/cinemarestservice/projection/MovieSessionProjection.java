package uz.pdp.cinemarestservice.projection;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.List;

public interface MovieSessionProjection {
//    UUID getId(); // seans id

    Integer getMovieAnnouncementId(); // afisha id

    Integer getMovieId();

    String getMovieTitle();

    Integer getMovieCoverImgId();

    Integer getStartDateId();

    LocalDate getStartDate();

    @Value("#{@hallRepo.getHallsAndTimesByMovieAnnouncementIdAndStartDateId(target.movieAnnouncementId, target.startDateId)}")
    List<HallAndTimesProjectionForSession> getHalls();
}
