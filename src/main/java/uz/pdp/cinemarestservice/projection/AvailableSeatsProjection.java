package uz.pdp.cinemarestservice.projection;

public interface AvailableSeatsProjection {

    Integer getId();

    Integer getSeatNumber();

    Integer getRowNumber();

    Boolean getAvailable();
}
