package uz.pdp.cinemarestservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinemarestservice.service.interfaces.MovieSessionService;
import uz.pdp.cinemarestservice.util.Constant;

@RestController
@RequestMapping("/api/movie-session")
public class MovieSessionController {


    @Autowired
    MovieSessionService movieSessionService;

    @GetMapping
    public HttpEntity getAllMovieSessions(
            @RequestParam(name = "size", defaultValue = Constant.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "search", defaultValue = "") String search
    ) {
        return movieSessionService.getAllMovieSessions(
                page,
                size,
                search
        );
    }
    
}
