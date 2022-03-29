package uz.pdp.cinemarestservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.cinemarestservice.model.Movie;
import uz.pdp.cinemarestservice.projection.CustomMovie;
import uz.pdp.cinemarestservice.projection.CustomMovieById;

import java.util.Optional;

public interface MovieRepo extends JpaRepository<Movie, Integer> {

    @Query(value = "select\n" +
            "       cast(m.id as varchar) as id,\n" +
            "       title,\n" +
            "       cast(release_date as date)as releaseDate,\n" +
            "       cast(a.id as varchar) as coverImgId\n" +
            "from movie m\n" +
            "join attachment a on a.id = m.cover_image_id\n" +
            "where lower(title) like lower(concat('%', :search, '%'))", nativeQuery = true)
    Page<CustomMovie> findAllByPage(Pageable pageable, @Param("search") String search);

    @Query(nativeQuery = true, value = "select" +
            " cast(m.id as varchar)            as id,\n" +
            "       m.title,\n" +
            "       m.release_date                   as releaseDate,\n" +
            "       cast(m.cover_image_id as varchar) as coverImgId\n" +
            //todo add more columns
            "from movie m " +
            "where m.id = :id")
    Optional<CustomMovieById> getMovieById(Integer id);

}
