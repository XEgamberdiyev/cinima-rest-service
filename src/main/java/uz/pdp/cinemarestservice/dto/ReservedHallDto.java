package uz.pdp.cinemarestservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservedHallDto {

    private Integer hallId;
    private String startDate;
    private String endDate;
    private List<String> startTimeList;
}
