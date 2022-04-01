package uz.pdp.cinemarestservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDto {

    @NotBlank(message = "Number can't be empty!")
    private String title;

    @NotBlank(message = "Description can't be empty!")
    private String description;

    @NotBlank(message = "Duration in minute can't be empty!")
    private Integer durationInMin;

    @NotBlank(message = "Minimum price can't be empty!")
    private Double minPrice;

    @NotBlank(message = "Image can't be empty!")
    private UUID coverImgId;

    @NotBlank(message = "Trailer can't be empty!")
    private UUID trailVideoId;

    @NotBlank(message = "Release date can't be empty!")
    private String releaseDate;


    private Double budget;

    @NotBlank(message = "Distributor can't be empty!")
    private UUID distributorId;

    @NotBlank(message = "Percentage can't be empty!")
    private Double distributorShareInPercentage;

    @NotBlank(message = "Actor can't be empty!")
    private List<UUID> actors;

    @NotBlank(message = "Director can't be empty!")
    private List<UUID> directors;

    @NotBlank(message = "Genres can't be empty!")
    private List<UUID> genres;
}
