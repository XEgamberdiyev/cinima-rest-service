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
@Entity(name = "refund_charge_fees")
public class RefundChargeFee extends AbsEntity {

    @Column(nullable = false)
    private Integer intervalMinutes;

    @Column(nullable = false)
    private Double percentage;

}
