package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinemarestservice.model.Hall;
import uz.pdp.cinemarestservice.projection.HallAndTimesProjectionForSession;

import java.util.List;

public interface HallRepo extends JpaRepository<Hall, Integer> {
    @Query(nativeQuery = true, value = "select distinct" +
            " cast(h.id as varchar) as id,\n" +
            "       h.name, \n" +
            " cast(rh.start_date_id as varchar) as startDateId,\n" +
            " cast(movie_announcement_id as varchar) as movieAnnouncementId\n" +
            "from hall h\n" +
            "         join movie_session rh on h.id = rh.hall_id\n" +
            "where rh.start_date_id = :startDateId\n" +
            "  and movie_announcement_id = :movieAnnouncementId")
    List<HallAndTimesProjectionForSession> getHallsAndTimesByMovieAnnouncementIdAndStartDateId(Integer movieAnnouncementId, Integer startDateId);


}
