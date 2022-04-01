package uz.pdp.cinemarestservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDto {

    private UUID ticketId;

    private UUID movieSessionId;

    private UUID seatId;
}
