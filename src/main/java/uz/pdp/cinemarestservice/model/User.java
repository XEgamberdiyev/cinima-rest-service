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
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;

    private String userName;

    private String password;

    private LocalDate dateOfBirth;

    @ManyToOne()
    private Gender gender;

    @ManyToMany()
    private List<Role> role;

    @ManyToMany
    private List<Permission> permission;
}
