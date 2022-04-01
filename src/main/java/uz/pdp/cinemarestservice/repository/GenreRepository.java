package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarestservice.model.Genre;

import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {

}
