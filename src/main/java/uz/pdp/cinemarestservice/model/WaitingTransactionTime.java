package uz.pdp.cinemarestservice.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import uz.pdp.cinemarestservice.model.abcClass.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import java.sql.Timestamp;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "waiting_ptransaction_times")
public class WaitingTransactionTime extends AbsEntity {

    @Column(nullable = false)
    private Integer minute;

    @OrderBy
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;
}
