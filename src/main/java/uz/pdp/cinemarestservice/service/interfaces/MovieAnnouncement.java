package uz.pdp.cinemarestservice.service.interfaces;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarestservice.dto.MovieAnnouncementDto;

@Service
public interface MovieAnnouncement {

    HttpEntity<?> getAllMovieAnnouncement();

    HttpEntity<?> getMovieAnnouncementById(Integer id);

    HttpEntity<?> addAndUpdateMovieAnnouncement(MovieAnnouncementDto movieAnnouncementDto);

}
