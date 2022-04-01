package uz.pdp.cinemarestservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemarestservice.model.Actor;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.service.ActorService;

import java.util.UUID;

@RestController
@RequestMapping("/api/actor")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @GetMapping
    public HttpEntity<?> getAllActors() {
        ApiResponse allActors = actorService.getAllActors();
        return ResponseEntity.status(allActors.isStatus() ? 200 : 204).body(allActors);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getActorById(@PathVariable UUID id) {
        ApiResponse actorById = actorService.getActorById(id);
        return ResponseEntity.status(actorById.isStatus() ? 200 : 404).body(actorById);
    }

    @PostMapping()
    public HttpEntity<?> addActor(@RequestPart("file") MultipartFile file, @RequestPart("json-actor") Actor actor) {
        ApiResponse apiResponse = actorService.addActor(file, actor);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editActor(@PathVariable UUID id, @RequestPart("file") MultipartFile file, @RequestPart("json-actor") Actor actor){
        ApiResponse apiResponse = actorService.editActor(id, file, actor);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteActor(@PathVariable UUID id){
        ApiResponse apiResponse = actorService.deleteActor(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }
}
