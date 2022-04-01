package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinemarestservice.model.TransactionHistory;

import java.util.Optional;
import java.util.UUID;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, UUID> {
    @Query(nativeQuery = true, value = "select ph.* " +
            "from ptransaction_histories ph " +
            "join ptransaction_histories_ticket_list phtl on ph.id = phtl.ptransaction_histories_id" +
            " where phtl.ticket_list_id =:ticketId")
    Optional<TransactionHistory> findTransactionHistoryByTicketId(UUID ticketId);
}
