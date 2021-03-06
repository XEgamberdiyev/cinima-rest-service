package uz.pdp.cinemarestservice.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class MovieAnnouncementDto {

    @NotNull(message = "MOVIE_ANNOUNCEMENT_REQUIRED")
    private UUID movieId;

    @NotBlank(message = "IS_ACTIVE_REQUIRED")
    private boolean isActive;
}
