package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarestservice.model.SessionDate;

public interface SessionDateRepo extends JpaRepository<SessionDate, Integer> {
}
