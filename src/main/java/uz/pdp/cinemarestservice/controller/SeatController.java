package uz.pdp.cinemarestservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarestservice.dtos.SeatDto;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.service.SeatServiceImpl;
import uz.pdp.cinemarestservice.util.Constants;

import java.util.UUID;

@RestController
@RequestMapping("/api/seat")
@RequiredArgsConstructor
public class SeatController {

    private final SeatServiceImpl seatService;

    @GetMapping
    public HttpEntity<?> getAllSeats(){
        ApiResponse apiResponse = seatService.getAllSeats();
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 204).body(apiResponse);
    }

    @GetMapping("/getSearByPage")
    public HttpEntity<?> getSeatByPage(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size
    ){
        ApiResponse apiResponse = seatService.getAllSeatsByPage(page, size);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/getSeatByRowId/{row_id}")
    public HttpEntity<?> getSeatByRowId(@PathVariable UUID row_id){
        ApiResponse apiResponse = seatService.getSeatByRowId(row_id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/getSeatHallId/{hall_id}")
    public HttpEntity<?> getSeatHallId(@PathVariable UUID hall_id){
        ApiResponse apiResponse = seatService.getSeatByHallId(hall_id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/getSeatById/{seat_id}")
    public HttpEntity<?> getSeatById(@PathVariable UUID seat_id){
        ApiResponse apiResponse = seatService.getSeatById(seat_id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addSeat(@RequestBody SeatDto seatDto){
        ApiResponse apiResponse = seatService.addSeat(seatDto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{seat_id}")
    public HttpEntity<?> editSeat(@PathVariable UUID seat_id, @RequestBody SeatDto seatDto){
        ApiResponse apiResponse = seatService.editSeat(seat_id, seatDto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{seat_id}")
    public HttpEntity<?> deleteSeat(@PathVariable UUID seat_id){
        ApiResponse apiResponse = seatService.deleteSeat(seat_id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/available-seats/{movieSessionId}")
    public HttpEntity<?> getAvailableSeats(@PathVariable UUID movieSessionId){
        ApiResponse apiResponse = seatService.getAvailableSeatsByMovieSessionId(movieSessionId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }
}
