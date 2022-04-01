package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarestservice.model.PriceCategory;

import java.util.UUID;

public interface PriceCategoryRepository extends JpaRepository<PriceCategory, UUID> {
}
