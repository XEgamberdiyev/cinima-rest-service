package uz.pdp.cinemarestservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarestservice.model.Director;

public interface DirectorRepo extends JpaRepository<Director, Integer> {
}
