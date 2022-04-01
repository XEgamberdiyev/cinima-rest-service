package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinemarestservice.model.Row;
import uz.pdp.cinemarestservice.projection.RowProjection;

import java.util.List;
import java.util.UUID;

public interface RowRepository extends JpaRepository<Row, UUID> {

    @Query(value = "select cast(r.id as varchar) as id, r.number, h.name as Name from hall_rows r " +
            "join halls h on h.id = r.hall_Id", nativeQuery = true)
    List<RowProjection> rowPrListRepos(UUID hallId);
}
