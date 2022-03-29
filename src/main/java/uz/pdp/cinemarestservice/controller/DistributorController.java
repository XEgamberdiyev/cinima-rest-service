package uz.pdp.cinemarestservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarestservice.model.Distributor;
import uz.pdp.cinemarestservice.repository.DistributorRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/distributor")
public class DistributorController {
    @Autowired
    DistributorRepo distributorRepo;

    @GetMapping
    public HttpEntity getAllDistributor() {
        List<Distributor> distributors = distributorRepo.findAll();
        return ResponseEntity.ok(distributors);
    }

    @GetMapping("/{id}")
    public HttpEntity getDistributorById(@PathVariable Integer id) {
        Optional<Distributor> optionalDistributor = distributorRepo.findById(id);
        Distributor distributor = new Distributor();
        if (optionalDistributor.isPresent()) {
            distributor = optionalDistributor.get();
            return ResponseEntity.ok(distributor);
        }
        return ResponseEntity.ok(distributor);
    }

    @PostMapping
    public HttpEntity addDistributor(@RequestBody Distributor distributor) {
        Distributor distributor1 = new Distributor();
        distributor1.setName(distributor.getName());
        distributor1.setDescription(distributor.getDescription());
        Distributor save = distributorRepo.save(distributor1);
        return ResponseEntity.ok(save);
    }

    @PutMapping("/{id}")
    public HttpEntity updateDistributor(@PathVariable Integer id,
                                            @RequestBody Distributor distributor){
        Optional<Distributor> optionalDistributor = distributorRepo.findById(id);
        if (optionalDistributor.isPresent()) {
            Distributor distributor1 = optionalDistributor.get();
            distributor1.setName(distributor.getName());
            distributor1.setDescription(distributor.getDescription());
            distributorRepo.save(distributor1);
        }
        return ResponseEntity.ok(new Distributor());
    }

    @DeleteMapping("/{id}")
    public HttpEntity deleteDistributor(@PathVariable Integer id){
        Optional<Distributor> optionalDistributor = distributorRepo.findById(id);
        if (optionalDistributor.isPresent()) {
            Distributor distributor = optionalDistributor.get();
            distributorRepo.delete(distributor);
        }
        return ResponseEntity.ok(new Distributor());
    }
}
