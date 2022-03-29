package uz.pdp.cinemarestservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarestservice.model.Hall;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;
import uz.pdp.cinemarestservice.service.HallServiceImpl;

@RestController
@RequestMapping("/api/hall")
public class HallController {

    @Autowired
    HallServiceImpl hallService;

    @GetMapping
    public HttpEntity<?> getAllHalls(){
        ApiResponse allHalls = hallService.getAllHalls();
        return ResponseEntity.status(allHalls.isSuccess()?200: 404).body(allHalls);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getHallById(@PathVariable Integer id){
        ApiResponse hallById = hallService.getHallById(id);
        return ResponseEntity.status(hallById.isSuccess()?200: 404).body(hallById);
    }

    @PostMapping
    public HttpEntity<?> addHall(@RequestBody Hall hall, Integer id){
        ApiResponse response = hallService.addHall(hall, id);
        return ResponseEntity.status(response.isSuccess()?200: 404).body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateHall(@PathVariable Integer id, @RequestBody Hall hall){
        ApiResponse response = hallService.addHall(hall, id);
        return ResponseEntity.status(response.isSuccess()?200: 404).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteHall(@PathVariable Integer id){
        ApiResponse response = hallService.deleteHall(id);
        return ResponseEntity.status(response.isSuccess()?200: 404).body(response);
    }
}
