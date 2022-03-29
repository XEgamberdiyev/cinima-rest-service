package uz.pdp.cinemarestservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Movie{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    private int durationInMinutes;

    @OneToOne
    private Attachment coverImage;

    private String trailerVideoUrl;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @ManyToMany
    private List<Genre> genre;

    private double minPrice;

    @ManyToOne
    private Distributor distributor;

    private double distributorShareInPercent;

    @ManyToMany
    private List<Actor> actor;


}
