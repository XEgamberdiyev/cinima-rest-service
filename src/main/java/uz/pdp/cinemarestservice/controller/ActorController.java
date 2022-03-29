package uz.pdp.cinemarestservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemarestservice.model.Actor;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;
import uz.pdp.cinemarestservice.repository.ActorRepo;
import uz.pdp.cinemarestservice.repository.AttachmentContentRepo;
import uz.pdp.cinemarestservice.repository.AttachmentRepo;
import uz.pdp.cinemarestservice.service.ActorService;

import java.io.IOException;

@RestController
@RequestMapping("/api/actor")
public class ActorController {

    @Autowired
    ActorRepo actorRepo;

    @Autowired
    AttachmentRepo attachmentRepo;

    @Autowired
    AttachmentContentRepo attachmentContentRepo;

    @Autowired
    ActorService actorService;

    @GetMapping
    public ApiResponse getActors() {
        return actorService.getAllActors();
    }

    @GetMapping("/{id}")
    public ApiResponse getActorById(@PathVariable Integer id) {
        return actorService.getActorById(id);
    }

    @PostMapping
    public ApiResponse addActor(@RequestPart(name = "actor") Actor actor,
                               @RequestPart(name = "file") MultipartFile request) throws IOException {
        return actorService.addActor(actor, request);
    }

    @PutMapping("/{id}")
    public ApiResponse updateActor(@PathVariable Integer id,
                                     @RequestPart(name = "actor") Actor actor,
                                     @RequestPart(name = "file") MultipartFile file) throws IOException {
        return actorService.updateActor(id, actor, file);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteActor(@PathVariable Integer id){
         return actorService.deleteActor(id);
    }

}
