package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarestservice.model.Director;

import java.util.UUID;

public interface DirectorRepository extends JpaRepository<Director, UUID> {
}
