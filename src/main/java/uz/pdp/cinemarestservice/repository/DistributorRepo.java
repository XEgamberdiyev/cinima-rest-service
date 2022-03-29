package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarestservice.model.Distributor;


public interface DistributorRepo extends JpaRepository<Distributor, Integer> {
}
