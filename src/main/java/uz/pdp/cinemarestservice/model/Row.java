package uz.pdp.cinemarestservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "h_row")
public class Row {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;

    private String name;

    @ManyToOne
    private Hall hall;

    @OneToMany(mappedBy = "row", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Seat> seat;
}
