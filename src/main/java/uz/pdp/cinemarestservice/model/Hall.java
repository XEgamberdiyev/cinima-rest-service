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
@Entity
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private double vipAdditionalFeeInPercent;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Row> row;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MovieSession> movieSessions;
}
