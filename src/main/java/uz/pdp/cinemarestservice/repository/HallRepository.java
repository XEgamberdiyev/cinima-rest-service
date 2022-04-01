package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinemarestservice.model.Hall;
import uz.pdp.cinemarestservice.projection.HallAndTimesProjectionForSession;

import java.util.List;
import java.util.UUID;

public interface HallRepository extends JpaRepository<Hall, UUID> {


    @Query(nativeQuery = true, value = "select\n" +
            "       cast(h.id as varchar) as id,\n" +
            "       h.name,\n" +
            "       cast(ms.movie_announcement_id as varchar) as movieAnnouncementId,\n" +
            "       cast(ms.start_date_id as varchar) as startDateId\n" +
            "from movie_sessions ms\n" +
            "join halls h on h.id = ms.hall_id\n" +
            "where ms.movie_announcement_id =:movieAnnouncementId\n" +
            "and ms.start_date_id = :startDateId\n" +
            "\n")
    List<HallAndTimesProjectionForSession> getHallsAndTimesByMovieAnnouncementIdAndStartDateId(UUID movieAnnouncementId, UUID startDateId);
}
