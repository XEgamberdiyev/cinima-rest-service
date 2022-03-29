package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarestservice.model.Row;

public interface RowRepo  extends JpaRepository<Row, Integer> {
}
