package uz.pdp.cinemarestservice.model;

import lombok.*;
import lombok.experimental.PackagePrivate;
import uz.pdp.cinemarestservice.model.abcClass.AbsEntity;
import uz.pdp.cinemarestservice.model.enums.TicketStatus;

import javax.persistence.*;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "tickets")
@PackagePrivate
public class Ticket extends AbsEntity {

    @OneToOne
    MovieSession movieSession;

    @OneToOne
    Seat seat;

    @OneToOne
    Attachment qrCode;

    @Column(nullable = false)
    Double price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    TicketStatus status;

    @ManyToOne
    User user;

}
