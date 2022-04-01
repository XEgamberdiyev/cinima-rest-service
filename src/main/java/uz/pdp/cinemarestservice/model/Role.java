package uz.pdp.cinemarestservice.model;

import lombok.*;
import uz.pdp.cinemarestservice.model.abcClass.AbsEntity;
import uz.pdp.cinemarestservice.model.enums.RoleEnum;

import javax.persistence.*;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "roles")
public class Role extends AbsEntity {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @ManyToMany
    private List<Permission> permissions;
}
