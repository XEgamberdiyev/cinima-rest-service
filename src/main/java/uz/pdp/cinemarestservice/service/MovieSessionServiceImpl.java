package uz.pdp.cinemarestservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarestservice.dto.MovieSessionDto;
import uz.pdp.cinemarestservice.dto.ReservedHallDto;
import uz.pdp.cinemarestservice.model.Hall;
import uz.pdp.cinemarestservice.model.MovieAnnouncement;
import uz.pdp.cinemarestservice.model.MovieSession;
import uz.pdp.cinemarestservice.model.SessionDate;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;
import uz.pdp.cinemarestservice.projection.MovieSessionProjection;
import uz.pdp.cinemarestservice.repository.HallRepo;
import uz.pdp.cinemarestservice.repository.MovieAnnouncementRepository;
import uz.pdp.cinemarestservice.repository.MovieSessionRepository;
import uz.pdp.cinemarestservice.repository.SessionDateRepo;
import uz.pdp.cinemarestservice.service.interfaces.MovieSessionService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {

    @Autowired
    MovieSessionRepository movieSessionRepository;
    @Autowired
    MovieAnnouncementRepository movieAnnouncementRepository;
    @Autowired
    HallRepo hallRepo;
    @Autowired
    SessionDateRepo sessionDateRepo;

    @Override
    public HttpEntity getAllMovieSessions(int page, int size, String search) {
        Pageable pageable = PageRequest.of(
                page - 1,
                size
        );
        Page<MovieSessionProjection> all = movieSessionRepository.findAllSessionsByPage(
                pageable,
                search);

        return ResponseEntity.ok(new ApiResponse("success", true, all));

    }

    @Override
    public HttpEntity<?> addMovieSession(MovieSessionDto movieSessionDto) {
        MovieSession movieSession = new MovieSession();
        Optional<MovieAnnouncement> optionalMovieAnnouncement = movieAnnouncementRepository.findById(movieSessionDto.getMovieAnnouncementId());
        if (optionalMovieAnnouncement.isPresent()) {
//            MovieAnnouncement movieAnnouncement = optionalMovieAnnouncement.get();
            List<ReservedHallDto> reservedHallDtoList = movieSessionDto.getReservedHallDtoList();
            for (ReservedHallDto reservedHallDto : reservedHallDtoList) {
                Optional<Hall> optionalHall = hallRepo.findById(reservedHallDto.getHallId());
                if (!optionalHall.isPresent()) {
                    return ResponseEntity.ok(new ApiResponse("Hall is not found", false, null));
                }
                movieSession.setHall(optionalHall.get());

                LocalDate startDate = LocalDate.parse(reservedHallDto.getStartDate());
                SessionDate sessionDate = new SessionDate();
                sessionDate.setDate(startDate);
                SessionDate saveSessionDate = sessionDateRepo.save(sessionDate);
                movieSession.setStartDate(saveSessionDate);

//                LocalTime startTime = LocalTime.parse(reservedHallDto.get)
                LocalTime endTime = LocalTime.parse(reservedHallDto.getEndDate());
            }
        }
        return null;
    }
}
