package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinemarestservice.model.WaitingTransactionTime;

import java.util.UUID;

public interface WaitingTimeRepository extends JpaRepository<WaitingTransactionTime, UUID> {

    @Query(nativeQuery = true,value = "select minute " +
            "from waiting_ptransaction_times " +
            "order by created_at desc " +
            "limit 1")
    Integer getWaitingMinute();
}
