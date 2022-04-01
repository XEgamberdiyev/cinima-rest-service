package uz.pdp.cinemarestservice.model;

//Egamberdiyev Xayitboy て { 01/04/22 } { 09:32 }

import lombok.*;
import uz.pdp.cinemarestservice.model.abcClass.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class MovieAnnouncement extends AbsEntity {

    @OneToOne
    private Movie movie;

    @Column(nullable = false)
    public boolean isActive;
}
