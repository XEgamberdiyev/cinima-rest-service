package uz.pdp.cinemarestservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.cinemarestservice.model.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "movie_announcements")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieAnnouncement extends AbsEntity {

    @OneToOne
    private Movie movie;

    private Boolean isActive;
}
