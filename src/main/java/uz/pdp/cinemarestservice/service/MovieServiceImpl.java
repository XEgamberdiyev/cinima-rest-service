package uz.pdp.cinemarestservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemarestservice.dto.MovieDto;
import uz.pdp.cinemarestservice.model.*;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;
import uz.pdp.cinemarestservice.projection.CustomMovie;
import uz.pdp.cinemarestservice.repository.*;
import uz.pdp.cinemarestservice.service.interfaces.MovieService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepo movieRepo;
    @Autowired
    AttachmentRepo attachmentRepo;
    @Autowired
    AttachmentContentRepo attachmentContentRepo;
    @Autowired
    ActorRepo actorRepo;
    @Autowired
    GenreRepo genreRepo;
    @Autowired
    DistributorRepo distributorRepo;
    @Autowired
    AttachmentService attachmentService;
    @Autowired
    MovieAnnouncementRepository movieAnnouncementRepository;

    @Override
    public ApiResponse getAllMovies() {
        List<Movie> movies = movieRepo.findAll();
        if (movies.size() != 0) {
            return new ApiResponse("Success", true, movies);
        }
        return new ApiResponse("Not found", false, null);
    }

    @Override
    public ApiResponse getMovieById(Integer id) {
        Optional<Movie> optionalMovie = movieRepo.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            return new ApiResponse("Success", true, movie);
        }
        return new ApiResponse("not found", false, null);
    }

    @Override
    public ApiResponse addMovie(MovieDto movieDto,
                                Integer id,
                                MultipartFile request) throws IOException {
        Movie movie = new Movie();

        if (id != null){
            Optional<Movie> optionalMovie = movieRepo.findById(id);
            if (optionalMovie.isPresent()) {
                Movie movie1 = optionalMovie.get();
                List<Integer> actorIds = movieDto.getActorIds();

                List<Actor> actorIdList = new ArrayList<>();
                for (Integer actorId : actorIds) {
                    Optional<Actor> optionalActor = actorRepo.findById(actorId);
                    if (!optionalActor.isPresent()) {
                        return new ApiResponse("Some Of Actor not found", false);
                    }
                    Actor actor = optionalActor.get();
                    actorIdList.add(actor);
                }

                List<Integer> genreIds = movieDto.getGenreIds();

                List<Genre> genreList = new ArrayList<>();
                for (Integer genreId : genreIds) {
                    Optional<Genre> optionalGenre = genreRepo.findById(genreId);
                    if (!optionalGenre.isPresent()) {
                        return new ApiResponse("Some Of genres not found", false);
                    }
                    Genre genre = optionalGenre.get();
                    genreList.add(genre);
                }

                Integer distributorId = movieDto.getDistributorId();
                Optional<Distributor> optionalDistributor = distributorRepo.findById(distributorId);
                if (!optionalDistributor.isPresent()) {
                    return new ApiResponse("Some Of distributor not found", false);
                }
                Distributor distributor = optionalDistributor.get();

                LocalDate releaseDate = movieDto.getReleaseDate();





                movie.setReleaseDate(releaseDate);
                movie1.setTitle(movieDto.getTitle());
                movie1.setDescription(movieDto.getDescription());
                movie1.setDurationInMinutes(movieDto.getDurationInMinutes());
                movie1.setTrailerVideoUrl(movieDto.getTrailerVideoUrl());
                movie1.setGenre(genreList);
                movie1.setMinPrice(movieDto.getMinPrice());
                movie1.setDistributor(distributor);
                movie1.setDistributorShareInPercent(movieDto.getDistributorShareInPercent());
                movie1.setActor(actorIdList);
                Attachment upload = attachmentService.upload(request);
                movie1.setCoverImage(upload);
//                MovieAnnouncement movieAnnouncement = new MovieAnnouncement();
//                movieAnnouncement.setMovie(movie);
//                movieAnnouncementRepository.save(movieAnnouncement);
                Movie save = movieRepo.save(movie1);
                return new ApiResponse("Success", true, save);
            }
        }

        List<Integer> actorIds = movieDto.getActorIds();

        List<Actor> actorIdList = new ArrayList<>();
        for (Integer actorId : actorIds) {
            Optional<Actor> optionalActor = actorRepo.findById(actorId);
            if (!optionalActor.isPresent()) {
                return new ApiResponse("Some Of Actor not found", false);
            }
            Actor actor = optionalActor.get();
            actorIdList.add(actor);
        }

        List<Integer> genreIds = movieDto.getGenreIds();

        List<Genre> genreList = new ArrayList<>();
        for (Integer genreId : genreIds) {
            Optional<Genre> optionalGenre = genreRepo.findById(genreId);
            if (!optionalGenre.isPresent()) {
                return new ApiResponse("Some Of genres not found", false);
            }
            Genre genre = optionalGenre.get();
            genreList.add(genre);
        }

        Integer distributorId = movieDto.getDistributorId();
        Optional<Distributor> optionalDistributor = distributorRepo.findById(distributorId);
        if (!optionalDistributor.isPresent()) {
            return new ApiResponse("Some Of distributor not found", false);
        }
        Distributor distributor = optionalDistributor.get();

        LocalDate releaseDate = movieDto.getReleaseDate();

        movie.setReleaseDate(releaseDate);
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setDurationInMinutes(movieDto.getDurationInMinutes());
        movie.setTrailerVideoUrl(movieDto.getTrailerVideoUrl());
        movie.setGenre(genreList);
        movie.setMinPrice(movieDto.getMinPrice());
        movie.setDistributor(distributor);
        movie.setDistributorShareInPercent(movieDto.getDistributorShareInPercent());
        movie.setActor(actorIdList);
        Attachment upload = attachmentService.upload(request);
        movie.setCoverImage(upload);
//        MovieAnnouncement movieAnnouncement = new MovieAnnouncement();
//        movieAnnouncement.setMovie(movie);
        Movie save = movieRepo.save(movie);
//        movieAnnouncementRepository.save(movieAnnouncement);
        return new ApiResponse("Success", true, save);
    }

    @Override
    public ApiResponse updateMovie(Integer id, MovieDto movieDto) {
        return null;
    }

    @Override
    public ApiResponse deleteMovie(Integer id) {
        Optional<Movie> optionalMovie = movieRepo.findById(id);
        if (optionalMovie.isPresent()) {
            movieRepo.delete(optionalMovie.get());
//            MovieAnnouncement movieAnnouncement = new MovieAnnouncement();
//            movieAnnouncement.setMovie(optionalMovie.get());
//            movieAnnouncementRepository.delete(movieAnnouncement);
            return new ApiResponse("Success", true);
        }
        return new ApiResponse("Movie is not found", false);
    }

    @Override
    public HttpEntity getAllMovies(int page, int size, String search, String sort, boolean direction) {
        Pageable pageable = PageRequest.of(
                page - 1,
                size,
                direction ? Sort.Direction.ASC : Sort.Direction.DESC,
                sort

        );
        try {
            Page<CustomMovie> all = movieRepo.findAllByPage(
                    pageable,
                    search);

            return ResponseEntity.ok(new ApiResponse("success", true, all));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("error", false, null));

        }
    }
}
