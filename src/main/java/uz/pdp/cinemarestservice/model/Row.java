package uz.pdp.cinemarestservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import uz.pdp.cinemarestservice.model.abcClass.AbsEntity;

import javax.persistence.*;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "hall_rows")
@JsonIgnoreProperties(value = {"seatList"})
public class Row extends AbsEntity {

    @Column(nullable = false)
    private Integer number;

    @ManyToOne
    private Hall hall;

    @JsonIgnore
    @OneToMany(mappedBy = "row", cascade = CascadeType.ALL)
    private List<Seat> seatList;

    public Row(Integer number, Hall hall) {
        this.number = number;
        this.hall = hall;
    }
}
