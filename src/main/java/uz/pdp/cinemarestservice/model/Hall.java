package uz.pdp.cinemarestservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import uz.pdp.cinemarestservice.model.abcClass.AbsEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "halls")
public class Hall extends AbsEntity {

    @Column(nullable = false, unique = true)
    private String name;

    private Double vipAdditionalFeeInPercent;

    @JsonIgnore
    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
    private List<Row> rowList;

    public Hall(String name) {
        this.name = name;
    }

    public Hall(String name, Double vipAdditionalFeeInPercent) {
        this.name = name;
        this.vipAdditionalFeeInPercent = vipAdditionalFeeInPercent;
    }
    
    
}
