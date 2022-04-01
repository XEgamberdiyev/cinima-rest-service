package uz.pdp.cinemarestservice.model;

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
@Entity(name = "directors")
public class Director extends AbsEntity {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String bio;

    @OneToOne
    private Attachment directorPhoto;
}
