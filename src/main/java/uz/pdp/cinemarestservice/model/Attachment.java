package uz.pdp.cinemarestservice.model;

import lombok.*;
import uz.pdp.cinemarestservice.model.abcClass.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "attachments")
public class Attachment extends AbsEntity {

    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private Long size;


}
