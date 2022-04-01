package uz.pdp.cinemarestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.projection.MovieSessionProjection;
import uz.pdp.cinemarestservice.repository.*;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionDateRepository dateRepository;
    private final SessionTimeRepository timeRepository;
    private final HallRepository hallRepository;
    private final SessionRepository sessionRepository;
    private final MovieRepository movieRepository;


    public ApiResponse getAllMovieSessions(int page, int size, String search) {
        Pageable pageable = PageRequest.of(
                page - 1,
                size
        );
        Page<MovieSessionProjection> all = sessionRepository.findAllSessionsByPage(
                pageable,
                search);
        if (all.isEmpty()) {
            return new ApiResponse("Error!!!",false);
        }
        return new ApiResponse("success", true, all);

    }

//    public ApiResponse addMovieSession(MovieSessionDto sessionDto){
//        Optional<MovieSession> sessionOptional = sessionRepository.findById(sessionDto.getMovieAnnouncementId());
//    }

//    public ApiResponse getAllSessions() {
//        List<MovieSession> sessionList = sessionRepository.findAll();
//        if (sessionList.size() == 0) {
//            return new ApiResponse("Not Found!!!", false);
//        }
//        return new ApiResponse("Success!", true, sessionList);
//    }

//    public ApiResponse addSession(MovieSessionDto movieSessionDto){
//        try {
//            MovieSession movieSession =
//        }
//    }
}
