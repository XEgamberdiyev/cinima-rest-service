package uz.pdp.cinemarestservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieSessionDto {

    private Integer movieAnnouncementId;

    private List<ReservedHallDto> reservedHallDtoList;

}
