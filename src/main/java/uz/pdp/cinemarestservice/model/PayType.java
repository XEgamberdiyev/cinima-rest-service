package uz.pdp.cinemarestservice.model;

import lombok.*;
import uz.pdp.cinemarestservice.model.abcClass.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "pay_types")
public class PayType extends AbsEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double commissionFeePercent;
}
