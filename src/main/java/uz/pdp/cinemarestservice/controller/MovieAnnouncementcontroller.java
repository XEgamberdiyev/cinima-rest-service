package uz.pdp.cinemarestservice.controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinemarestservice.dto.MovieAnnouncementDto;

@RestController
@RequestMapping("/api/movieAnnouncement")
public class MovieAnnouncementcontroller {

    public HttpEntity<?> getAllMovieAnnouncement(){
     return null;
    }

    public HttpEntity<?> getMovieAnnouncement(@PathVariable Integer id){
        return null;
    }

    public HttpEntity<?> addAndUpdateMovieAnnouncement(@RequestPart MovieAnnouncementDto movieAnnouncementDto, @PathVariable Integer id){
        return null;
    }

    public HttpEntity<?> deleteMovieAnnouncement(@PathVariable Integer id){
        return null;
    }

}
