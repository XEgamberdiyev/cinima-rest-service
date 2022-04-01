package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarestservice.model.Actor;

import java.util.UUID;

public interface ActorRepository extends JpaRepository<Actor, UUID> {
}
