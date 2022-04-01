package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinemarestservice.model.Ticket;
import uz.pdp.cinemarestservice.model.enums.TicketStatus;
import uz.pdp.cinemarestservice.projection.CustomTicketForCart;
import uz.pdp.cinemarestservice.projection.TicketProjection;

import java.util.List;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    @Query(nativeQuery = true, value = "select cast(id as varchar), status from tickets where id = :id")
    CustomTicketForCart getTicketByIdForCart(UUID id);

    @Query(nativeQuery = true, value = "select distinct * from tickets" +
            " where movie_session_id = :movieSessionId" +
            " and seat_id = :seatId" +
            " and status <> 'REFUNDED'")
    CustomTicketForCart checkSeatIfAvailable(UUID movieSessionId, UUID seatId);

    @Query(nativeQuery = true, value = "select m.title,\n" +
            "       t.price\n" +
            "from tickets t\n" +
            "         join movie_sessions ms on ms.id = t.movie_session_id\n" +
            "         join movie_announcement ma on ma.id = ms.movie_announcement_id\n" +
            "         join movies m on ma.movie_id = m.id\n" +
            "            where t.user_id= :userId\n")
    List<TicketProjection> getTicketsByUserId(UUID userId);


    List<Ticket> findByUserIdAndAndStatus(UUID userId, TicketStatus status);
}
