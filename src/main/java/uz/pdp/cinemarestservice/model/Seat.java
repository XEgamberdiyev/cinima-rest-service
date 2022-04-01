package uz.pdp.cinemarestservice.model;

import lombok.*;
import uz.pdp.cinemarestservice.model.abcClass.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "seats")
public class Seat extends AbsEntity {

    @Column(nullable = false)
    private Integer number;

    @ManyToOne
    private Row row;

    @ManyToOne
    private PriceCategory priceCategories;
}
