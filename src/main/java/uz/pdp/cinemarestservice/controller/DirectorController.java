package uz.pdp.cinemarestservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemarestservice.model.Director;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;
import uz.pdp.cinemarestservice.service.DirectorService;

import java.io.IOException;

@RestController
@RequestMapping("/api/director")
public class DirectorController {

    @Autowired
    DirectorService directorService;
    @GetMapping
    public ApiResponse getAllDirector(){
        return directorService.getAllDirectors();
    }

    @GetMapping("/{id}")
    public ApiResponse getDirectorById(@PathVariable Integer id){
        return directorService.getDirectorById(id);
    }

    @PostMapping
    public ApiResponse addDirector(@RequestPart(name = "director") Director director,
                                   @RequestPart(name = "file") MultipartFile request) throws IOException {
        return directorService.addDirector(director, request);
    }

    @PutMapping("/{id}")
    public ApiResponse updateDirector(@PathVariable Integer id,
                                      @RequestPart(name = "director") Director director,
                                      MultipartFile file) throws IOException {
        return directorService.updateDirector(id, director, file);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteDirector(@PathVariable Integer id){
        return directorService.deleteDirector(id);
    }
}
