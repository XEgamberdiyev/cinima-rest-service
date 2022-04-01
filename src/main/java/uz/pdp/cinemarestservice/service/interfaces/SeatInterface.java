package uz.pdp.cinemarestservice.service.interfaces;

import uz.pdp.cinemarestservice.dtos.SeatDto;
import uz.pdp.cinemarestservice.poyload.ApiResponse;

import java.util.UUID;

public interface SeatInterface {

    ApiResponse getAllSeatsByPage(int page, int size);
    ApiResponse getAllSeats();
    ApiResponse getSeatByRowId(UUID rowId);
    ApiResponse getSeatByHallId(UUID halId);
    ApiResponse getSeatById(UUID seatId);
    ApiResponse addSeat(SeatDto seatDto);
    ApiResponse editSeat(UUID seatId, SeatDto seatDto);
    ApiResponse deleteSeat(UUID seatId);

}
