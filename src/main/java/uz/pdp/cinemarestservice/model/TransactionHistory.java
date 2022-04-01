package uz.pdp.cinemarestservice.model;

import lombok.*;
import uz.pdp.cinemarestservice.model.abcClass.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "ptransaction_histories")
public class TransactionHistory extends AbsEntity {


    @OneToMany
    private List<Ticket> ticketList;

    @OneToOne
    private PayType payType;

    private String stripePaymentIntent;

 //   private Date date;

}
