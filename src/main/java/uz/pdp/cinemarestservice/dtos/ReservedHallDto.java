package uz.pdp.cinemarestservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservedHallDto {

    private UUID halId;
    private String startDate;
    private String endDate;
    private List<String> startTime;
}
