package uz.pdp.cinemarestservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.cinemarestservice.model.template.AbsEntity;

import javax.persistence.Entity;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "session_date")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessionDate extends AbsEntity {

   private LocalDate date;
}
