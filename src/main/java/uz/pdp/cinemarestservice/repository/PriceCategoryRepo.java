package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarestservice.model.PriceCotegory;

public interface PriceCategoryRepo extends JpaRepository<PriceCotegory, Integer> {
}
