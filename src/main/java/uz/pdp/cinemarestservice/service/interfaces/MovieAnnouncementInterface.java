package uz.pdp.cinemarestservice.service.interfaces;


import uz.pdp.cinemarestservice.dtos.MovieAnnouncementDto;
import uz.pdp.cinemarestservice.poyload.ApiResponse;

import java.util.UUID;

public interface MovieAnnouncementInterface {

    ApiResponse getAllMovieAnnouncement(int page, int size);
    ApiResponse getMovieAnnouncementById(UUID movieAnnouncement_id);
    ApiResponse addMovieAnnouncement(MovieAnnouncementDto movieAnnouncementDto);
    ApiResponse editMovieAnnouncement(UUID movieAnnouncementId, MovieAnnouncementDto movieAnnouncementDto);
    ApiResponse deleteMovieAnnouncement(UUID movieAnnouncementId);
}
