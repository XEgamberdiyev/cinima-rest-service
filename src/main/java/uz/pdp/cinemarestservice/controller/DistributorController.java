package uz.pdp.cinemarestservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarestservice.model.Distributor;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.service.DistributorService;

import java.util.UUID;

@RestController
@RequestMapping("/api/distributor")
@RequiredArgsConstructor
public class DistributorController {

    private final DistributorService distributorService;

    @GetMapping
    public HttpEntity<?> getAllDistributors(){
        ApiResponse apiResponse = distributorService.getAllDistributors();
        return ResponseEntity.status(apiResponse.isStatus()?200:204).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getDistributors(@PathVariable UUID id){
        ApiResponse apiResponse = distributorService.getDistributors(id);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addDistributors(@RequestBody Distributor distributor){
        ApiResponse apiResponse = distributorService.addDistributor(distributor);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editDistributors(@PathVariable UUID id,@RequestBody Distributor distributor){
        ApiResponse apiResponse = distributorService.editDistributor(id,distributor);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteDistributors(@PathVariable UUID id){
        ApiResponse apiResponse = distributorService.deleteDistributor(id);
        return ResponseEntity.status(apiResponse.isStatus()?200:404).body(apiResponse);
    }
}
