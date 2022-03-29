package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarestservice.model.Genre;

public interface GenreRepo extends JpaRepository<Genre, Integer> {
}
