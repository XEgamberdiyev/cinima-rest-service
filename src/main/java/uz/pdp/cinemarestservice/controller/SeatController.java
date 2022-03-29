package uz.pdp.cinemarestservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinemarestservice.service.SeatService;

@RestController
@RequestMapping("/api/seat")
public class SeatController {

//    public HttpResponse getAllSeat
    @Autowired
SeatService seatService;

    @GetMapping("/available-seats/{movieSessionId}")
    public HttpEntity<?> getAvailableSeatsBySessionId(@PathVariable Integer movieSessionId) {
        return seatService.getAvailableSeatsBySessionId(movieSessionId);
    }
}
