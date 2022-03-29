package uz.pdp.cinemarestservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDto {
    private String title;

    private String description;

    private int durationInMinutes;

    private MultipartHttpServletRequest request;

    private String trailerVideoUrl;

    private List<Integer> genreIds;

    private double minPrice;

    private Integer distributorId;

    private double distributorShareInPercent;

    private List<Integer> actorIds;

    private LocalDate releaseDate;
}
