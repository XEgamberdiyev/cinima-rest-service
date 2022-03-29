package uz.pdp.cinemarestservice.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface HallAndTimesProjectionForSession {

    Integer getId();

    String getName();

//
//    UUID getMovieAnnouncementId();
//
//    UUID getStartDateId();

    @Value("#{@sessionTimeRepository.getTimesByHallIdAndAnnouncementIdAndStartDateId(target.id, target.movieAnnouncementId, target.startDateId)}")
    List<SessionTimeProjection> getTimes();


}
