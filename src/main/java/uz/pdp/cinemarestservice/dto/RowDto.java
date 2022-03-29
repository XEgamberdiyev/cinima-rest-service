package uz.pdp.cinemarestservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RowDto {
    private String name;
    private Integer hallId;
}
