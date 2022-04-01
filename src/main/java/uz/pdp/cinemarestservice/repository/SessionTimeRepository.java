package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinemarestservice.model.SessionTime;
import uz.pdp.cinemarestservice.projection.SessionTimeProjection;

import java.util.List;
import java.util.UUID;

public interface SessionTimeRepository extends JpaRepository<SessionTime, UUID> {

    @Query(nativeQuery = true, value = "select\n" +
            "       cast(st.id as varchar) as id,\n" +
            "       st.time,\n" +
            "       cast(ms.id as varchar) as sessionId\n" +
            "from session_times st\n" +
            "join movie_sessions ms on st.id = ms.start_time_id\n" +
            "where ms.hall_id = :hallId and " +
            "      ms.movie_announcement_id = :movieAnnouncementId and\n" +
            "      ms.start_date_id = :startDateId")
    List<SessionTimeProjection> getAllTimesByHallIdAndAnnouncementIdAndStartDateId(UUID hallId, UUID movieAnnouncementId, UUID startDateId);
}
