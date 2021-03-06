package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.cinemarestservice.model.User;
import uz.pdp.cinemarestservice.projection.CustomTicket;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(nativeQuery = true, value = "select distinct cast(t.id as varchar),\n" +
            "                s.number  as seatNumber,\n" +
            "                hr.number as rowNumber,\n" +
            "                h.name    as hallName,\n" +
            "                (coalesce(pc.additional_fee_in_percent, 0) * m.min_price / 100) +\n" +
            "                (coalesce(h.vipAdditionalFeeInPercent, 0) * m.min_price / 100) +\n" +
            "                m.min_price as price, \n" +
            "                cast(ms.id as varchar) as movieSessionId,\n" +
            "                m.title as movieName,\n" +
            "                sd.date as startDate,\n" +
            "                st.time as startTime\n" +
            "from tickets t\n" +
            "         join seats s on t.seat_id = s.id\n" +
            "         join hall_rows hr on hr.id = s.row_id\n" +
            "         join halls h on h.id = hr.hall_id\n" +
            "         join movie_sessions ms on t.movie_session_id = ms.id\n" +
            "         join movie_announcement ma on ms.movie_announcement_id = ma.id\n" +
            "         join movies m on m.id = ma.movie_id\n" +
            "         join price_categories pc on pc.id = s.price_categories_id\n" +
            "         join session_dates sd on sd.id = ms.start_date_id\n" +
            "         join session_times st on st.id = ms.start_time_id\n" +
            "where t.status <> 'NEW' and\n" +
            "      t.user_id =:userId")
    List<CustomTicket> getTicketsInTheCart(UUID userId);

    @Query(nativeQuery = true, value = "delete\n" +
            "from tickets\n" +
            "where user_id =:userId\n" +
            "and status <> 'NEW'")
    void clearCartOfUser(UUID userId);


    @Query(nativeQuery = true, value = "delete\n" +
            "from tickets t\n" +
            "where status <> 'NEW'\n" +
            "and user_id =:userId\n" +
            "and id =:ticketId ")
    void deleteTicketFromCart(UUID userId, UUID ticketId);

    User findByUserName(String username);
}
