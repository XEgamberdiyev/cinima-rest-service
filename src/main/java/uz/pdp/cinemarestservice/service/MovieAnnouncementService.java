package uz.pdp.cinemarestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.cinemarestservice.dtos.MovieAnnouncementDto;
import uz.pdp.cinemarestservice.model.Movie;
import uz.pdp.cinemarestservice.model.MovieAnnouncement;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.repository.MovieAnnouncementRepository;
import uz.pdp.cinemarestservice.repository.MovieRepository;
import uz.pdp.cinemarestservice.service.interfaces.MovieAnnouncementInterface;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieAnnouncementService implements MovieAnnouncementInterface {

    private final MovieAnnouncementRepository movieAnnouncementRepository;
    private final MovieRepository movieRepository;

    @Override
    public ApiResponse getAllMovieAnnouncement(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MovieAnnouncement> movieAnnouncements = movieAnnouncementRepository.findAll(pageable);
        if (movieAnnouncements.getSize() == 0){
            return new ApiResponse("Not found!!!",false);
        }
        return new ApiResponse("Success!", false);
    }

    @Override
    public ApiResponse getMovieAnnouncementById(UUID movieAnnouncement_id) {
        Optional<MovieAnnouncement> optionalMovieAnnouncement = movieAnnouncementRepository.findById(movieAnnouncement_id);
        return optionalMovieAnnouncement.map(movieAnnouncement -> new ApiResponse("Success!", true, movieAnnouncement)).orElseGet(() -> new ApiResponse("Not Found!!!", false));
    }

    @Override
    public ApiResponse addMovieAnnouncement(MovieAnnouncementDto movieAnnouncementDto) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieAnnouncementDto.getMovieId());
        if (!optionalMovie.isPresent()) {
            return new ApiResponse("Not Found!!!", false);
        }
        try {
            MovieAnnouncement saveMovieAnnouncement = movieAnnouncementRepository.save( new MovieAnnouncement(optionalMovie.get(),
                    movieAnnouncementDto.isActive()));
        return new ApiResponse("Success!", true, saveMovieAnnouncement);
        } catch (Exception e){
            return new ApiResponse("Error!!!", false);
        }
    }

    @Override
    public ApiResponse editMovieAnnouncement(UUID movieAnnouncement_id, MovieAnnouncementDto movieAnnouncementDto) {
        Optional<MovieAnnouncement> optionalMovieAnnouncement = movieAnnouncementRepository.findById(movieAnnouncement_id);
        if (!optionalMovieAnnouncement.isPresent()) {
            return new ApiResponse("Movie Announcement Not Found!!!", false);
        }
        Optional<Movie> optionalMovie = movieRepository.findById(movieAnnouncementDto.getMovieId());
        if (!optionalMovie.isPresent()) {
            return new ApiResponse("Movie Not Found!!!", false);
        }
        try {
            MovieAnnouncement editMovieAnnouncement = optionalMovieAnnouncement.get();
            editMovieAnnouncement.setMovie(optionalMovie.get());
            editMovieAnnouncement.setActive(movieAnnouncementDto.isActive());
            MovieAnnouncement saveMovieAnnouncement = movieAnnouncementRepository.save(editMovieAnnouncement);
            return new ApiResponse("Success!", true, saveMovieAnnouncement);
        } catch (Exception e){
            return new ApiResponse("Error!!! Not Added!", false);
        }
    }

    @Override
    public ApiResponse deleteMovieAnnouncement(UUID movieAnnouncement_id) {
        Optional<MovieAnnouncement> optionalMovieAnnouncement = movieAnnouncementRepository.findById(movieAnnouncement_id);
        if (!optionalMovieAnnouncement.isPresent()) {
            return new ApiResponse("Movie Announcement Not Found!!!", false);
        }
        try {
            movieAnnouncementRepository.deleteById(movieAnnouncement_id);
            return new ApiResponse("Success!",true);
        } catch (Exception e){
            return new ApiResponse("Error!!! Not Deleted!", false);
        }
    }
}
