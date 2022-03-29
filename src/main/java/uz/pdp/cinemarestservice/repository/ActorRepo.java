package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarestservice.model.Actor;

public interface ActorRepo extends JpaRepository<Actor, Integer> {
}
