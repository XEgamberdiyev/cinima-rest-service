package uz.pdp.cinemarestservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class MovieSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @ManyToOne
//    private MovieAnnouncement movieAnnouncement;

    @ManyToOne
    private Hall hall;

    @ManyToOne
    private SessionDate startDate;

    @ManyToOne
    private SessionTime startTime;

    @ManyToOne
    private SessionTime endTime;

//    @OneToMany(mappedBy = "movieSession", cascade = CascadeType.ALL)
//    private List<SessionDate> sessionDates;

//    @OneToMany(mappedBy = "movieSession", cascade = CascadeType.ALL)
//    private List<Ticket> tickets;
 }
