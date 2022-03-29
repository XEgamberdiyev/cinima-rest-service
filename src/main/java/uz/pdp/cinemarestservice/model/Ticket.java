package uz.pdp.cinemarestservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cinemarestservice.model.enam.TicketStatus;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private MovieSession movieSession;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @OneToOne
    private Seat seat;

    private String qrCode;

    private double price;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

}
