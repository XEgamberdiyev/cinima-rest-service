package uz.pdp.cinemarestservice.model;

import lombok.*;
import uz.pdp.cinemarestservice.model.abcClass.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "sessions_halls")
public class SessionHall extends AbsEntity {

    @ManyToOne
    private MovieSession movieSession;

    @ManyToOne
    private Hall hall;
}
