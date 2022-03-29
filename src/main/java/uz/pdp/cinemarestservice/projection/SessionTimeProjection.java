package uz.pdp.cinemarestservice.projection;

import java.time.LocalTime;

public interface SessionTimeProjection {

    Integer getId();

    LocalTime getTime();

    Integer getSessionId();

}
