package uz.pdp.cinemarestservice.projection;

import java.util.UUID;

public interface AvailableProjection {

        UUID getId();

        Integer getSeatNumber();

        boolean isAvailable();

        Integer getRowNumber();
}
