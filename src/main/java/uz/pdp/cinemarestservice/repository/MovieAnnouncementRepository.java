package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarestservice.model.MovieAnnouncement;


public interface MovieAnnouncementRepository extends JpaRepository<MovieAnnouncement, Integer> {

}
