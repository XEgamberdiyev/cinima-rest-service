package uz.pdp.cinemarestservice.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.cinemarestservice.model.abcClass.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "actors")
public class Actor extends AbsEntity {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String bio;

    @OneToOne
    private Attachment actorPhoto;

    public Actor(String fullName, String bio, Attachment actorPhoto) {
        this.fullName = fullName;
        this.bio = bio;
        this.actorPhoto = actorPhoto;
    }
}
