package uz.pdp.cinemarestservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.cinemarestservice.model.template.AbsEntity;

import javax.persistence.Entity;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "session_time")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessionTime extends AbsEntity {

    LocalTime time;
}
