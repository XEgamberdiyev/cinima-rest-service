package uz.pdp.cinemarestservice.model;

import lombok.*;
import uz.pdp.cinemarestservice.model.abcClass.AbsEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "movies")
public class Movie extends AbsEntity {

    @Column(nullable = false, unique = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer durationInMinutes;

    @Column(nullable = false)
    private Double minPrice;

    @OneToOne
    private Attachment coverImage;

    @OneToOne
    private Attachment trailerVideo;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false)
    private Double budget;

    @OneToMany
    private List<Actor> actors;

    @OneToMany
    private List<Director> directors;

    @ManyToMany
    private List<Genre> genres;


    @OneToOne
    private Distributor distributor;

    @Column(nullable = false)
    private Double distributorShareInPercent;

    public Movie(String title, String description, Integer durationInMinutes, Double minPrice, Attachment coverImage, Attachment trailerVideo, LocalDate releaseDate, Double budget, Double distributorShareInPercent) {
        this.title = title;
        this.description = description;
        this.durationInMinutes = durationInMinutes;
        this.minPrice = minPrice;
        this.coverImage = coverImage;
        this.trailerVideo = trailerVideo;
        this.releaseDate = releaseDate;
        this.budget = budget;
        this.distributorShareInPercent = distributorShareInPercent;
    }
}
