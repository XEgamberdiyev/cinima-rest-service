package uz.pdp.cinemarestservice.model;

import lombok.*;
import uz.pdp.cinemarestservice.model.abcClass.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "night_session_add_fee")
public class NightSessionAddFee extends AbsEntity {

    @Column(nullable = false)
    private Double percentage;

    @Column(nullable = false)
    private Date date;
}
