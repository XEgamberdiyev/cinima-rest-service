package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinemarestservice.model.SessionTime;
import uz.pdp.cinemarestservice.projection.SessionTimeProjection;

import java.util.List;

public interface SessionTimeRepository extends JpaRepository<SessionTime, Integer> {


    @Query(value = "select distinct " +
            "cast(st.id as varchar) as id,\n" +
            "cast(ms.id as varchar) as sessionId,\n" +
            "       time\n" +
            "from session_time st\n" +
            "         join movie_session ms on st.id = ms.start_time_id\n" +
            "where ms.hall_id = :hallId\n" +
            "  and movie_announcement_id = :movieAnnouncementId " +
            "and ms.start_date_id = :startDateId", nativeQuery = true)
    List<SessionTimeProjection> getTimesByHallIdAndAnnouncementIdAndStartDateId(Integer hallId,
                                                                                Integer movieAnnouncementId, Integer startDateId);


}
