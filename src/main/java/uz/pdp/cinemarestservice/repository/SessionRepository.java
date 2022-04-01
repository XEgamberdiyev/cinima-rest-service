package uz.pdp.cinemarestservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinemarestservice.model.MovieSession;
import uz.pdp.cinemarestservice.projection.MovieSessionProjection;

import java.util.UUID;

public interface SessionRepository extends JpaRepository<MovieSession, UUID> {

    @Query(nativeQuery = true, value = "select distinct " +
            "cast(ma.id as varchar) as movieAnnouncementId,\n" +
            "       cast(ma.movie_id as varchar) as movieId,\n" +
            "       m.title as movieTitle,\n" +
            "       cast(m.cover_image_id as varchar) as movieCoverImgId,\n" +
            "       cast(sd.id as varchar) as startDateId,\n" +
            "       sd.date as startDate\n" +
            "from movie_sessions ms\n" +
            "join movie_announcement ma on ma.id = ms.movie_announcement_id\n" +
            "join session_dates sd on sd.id = ms.start_date_id\n" +
            "join movies m on ma.movie_id = m.id\n" +
            "where lower(m.title) like lower(concat('%',:search,'%')) and sd.date >= cast(now() as date)\n" +
            "            order by sd.date")
    Page<MovieSessionProjection> findAllSessionsByPage(Pageable pageable, String search);
}
