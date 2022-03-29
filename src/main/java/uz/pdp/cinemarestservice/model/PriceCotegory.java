package uz.pdp.cinemarestservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class PriceCotegory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private double additionalFeeInPercent;

    private int color;

    @OneToMany(mappedBy = "priceCategory", cascade = CascadeType.ALL)
    private List<Seat> seats;
}
