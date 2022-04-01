package uz.pdp.cinemarestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarestservice.dtos.MovieDto;
import uz.pdp.cinemarestservice.model.*;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.projection.CustomMovie;
import uz.pdp.cinemarestservice.repository.*;
import uz.pdp.cinemarestservice.service.interfaces.MovieInterface;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieInterface {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;
    private final DistributorRepository distributorRepository;
    private final AttachmentRepository attachmentRepository;
    private final GenreRepository genreRepository;

    @Override
    public ApiResponse getAllMovies(int page, int size, String search, String sort, boolean direction) {
        Pageable pageable = PageRequest.of(page - 1, size, direction ? Sort.Direction.ASC : Sort.Direction.DESC, sort);
        try {
            Page<CustomMovie> all = movieRepository.findAllByPage(pageable, search);
            return new ApiResponse("Success!", true, all);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);
        }
    }

    @Override
    public ApiResponse getMovieById(UUID id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            return new ApiResponse("Movie not found!", false);
        }
        return new ApiResponse("Success!", true, optionalMovie.get());
    }

    @Override
    public ApiResponse addMovie(MovieDto movieDto) {
        List<Actor> actorList = new ArrayList<>();
        movieDto.getActors().forEach(uuid -> actorRepository.findById(uuid).ifPresent(actorList::add));
        if (actorList.isEmpty()) {
            return new ApiResponse("Actors is empty!!!", false);
        }
        List<Director> directorList = new ArrayList<>();
        movieDto.getDirectors().forEach(uuid -> directorRepository.findById(uuid).ifPresent(directorList::add));
        if (directorList.isEmpty()) {
            return new ApiResponse("Directors is empty!!!", false);
        }
        List<Genre> genreList = new ArrayList<>();
        movieDto.getGenres().forEach(uuid -> genreRepository.findById(uuid).ifPresent(genreList::add));
        if (genreList.isEmpty()) {
            return new ApiResponse("Genre is empty!!!", false);
        }
        Optional<Attachment> optionalAttachmentImage = attachmentRepository.findById(movieDto.getCoverImgId());
        if (optionalAttachmentImage.isPresent()) {
            return new ApiResponse("Cover Image not found!!!", false);
        }
        Optional<Attachment> optionalAttachmentTrailer = attachmentRepository.findById(movieDto.getTrailVideoId());
        if (optionalAttachmentTrailer.isPresent()) {
            return new ApiResponse("Trailer not found!!!", false);
        }
        Optional<Distributor> optionalDistributor = distributorRepository.findById(movieDto.getDistributorId());
        if (optionalDistributor.isPresent()) {
            return new ApiResponse("Distributor not found!!!", false);
        }

        try {
            Movie newMovie = new Movie();
            newMovie.setTitle(movieDto.getTitle());
            newMovie.setDescription(movieDto.getDescription());
            newMovie.setDurationInMinutes(movieDto.getDurationInMin());
            newMovie.setMinPrice(movieDto.getMinPrice());
            newMovie.setCoverImage(optionalAttachmentImage.get());
            newMovie.setTrailerVideo(optionalAttachmentTrailer.get());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(movieDto.getReleaseDate());
            newMovie.setReleaseDate(date);
            newMovie.setBudget(movieDto.getBudget());
            newMovie.setDistributor(optionalDistributor.get());
            newMovie.setDistributorShareInPercent(movieDto.getDistributorShareInPercentage());
            newMovie.setActors(actorList);
            newMovie.setDirectors(directorList);
            newMovie.setGenres(genreList);
            Movie saveMovie = movieRepository.save(newMovie);
            return new ApiResponse("Success!", true, saveMovie);

        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);
        }
    }

    @Override
    public ApiResponse editMovie(UUID id, MovieDto movieDto) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (!optionalMovie.isPresent()) return new ApiResponse("Movie not found!", false);

        List<Actor> actors = new ArrayList<>();
        movieDto.getActors().forEach(uuid -> actorRepository.findById(uuid).ifPresent(actors::add));
        if (actors.isEmpty()) return new ApiResponse("Actors is empty!", false);

        List<Director> directors = new ArrayList<>();
        movieDto.getDirectors().forEach(uuid -> directorRepository.findById(uuid).ifPresent(directors::add));
        if (directors.isEmpty()) return new ApiResponse("Directors is empty!", false);

        List<Genre> genres = new ArrayList<>();
        movieDto.getGenres().forEach(uuid -> genreRepository.findById(uuid).ifPresent(genres::add));
        if (genres.isEmpty()) return new ApiResponse("Genres is empty!", false);

        Optional<Attachment> optionalAttachmentImg = attachmentRepository.findById(movieDto.getCoverImgId());
        if (!optionalAttachmentImg.isPresent()) return new ApiResponse("Cover image is empty!", false);

        Optional<Attachment> optionalAttachmentVideo = attachmentRepository.findById(movieDto.getTrailVideoId());
        if (!optionalAttachmentVideo.isPresent()) return new ApiResponse("Trailer video is empty!", false);

        Optional<Distributor> optionalDistributor = distributorRepository.findById(movieDto.getDistributorId());
        if (!optionalDistributor.isPresent()) return new ApiResponse("Distributor is empty!", false);

        try {
            Movie movie = optionalMovie.get();
            movie.setTitle(movieDto.getTitle());
            movie.setDescription(movieDto.getDescription());
            movie.setDurationInMinutes(movie.getDurationInMinutes());
            movie.setMinPrice(movieDto.getMinPrice());
            movie.setCoverImage(optionalAttachmentImg.get());
            movie.setTrailerVideo(optionalAttachmentVideo.get());
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(movieDto.getReleaseDate());
            movie.setReleaseDate(date);
            movie.setBudget(movieDto.getBudget());
            movie.setDistributor(optionalDistributor.get());
            movie.setDistributorShareInPercent(movieDto.getDistributorShareInPercentage());
            movie.setActors(actors);
            movie.setDirectors(directors);
            movie.setGenres(genres);
            Movie saveMovie = movieRepository.save(movie);
            return new ApiResponse("Successfully edited!", true, saveMovie);
        } catch (Exception e) {
            return new ApiResponse("Error!!", false);
        }

    }

    @Override
    public ApiResponse deleteMovie(UUID id) {
        try {
            movieRepository.deleteById(id);
            return new ApiResponse("Success!", true);
        } catch (Exception e) {
            return new ApiResponse("Movie not found!", false);
        }
    }
}
