package uz.pdp.cinemarestservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SeatDto {

    @NotBlank(message = "Number can't be empty!")
    private Integer number;

    @NotBlank(message = "Row can't be empty!")
    private UUID rowId;

    @NotBlank(message = "Price category can't be empty!")
    private UUID priceCategoryId;

}
