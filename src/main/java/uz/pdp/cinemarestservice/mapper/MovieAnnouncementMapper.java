package uz.pdp.cinemarestservice.mapper;//package uz.pdp.cinemarestservice.mapper;
//
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.ReportingPolicy;
//import org.springframework.stereotype.Component;
//import uz.pdp.cinemarestservice.model.M.MovieAnnouncement;
//import uz.pdp.cinemarestservice.poyload.dtos.MovieAnnouncementDto;
//
//@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
//@Component
//public interface MovieAnnouncementMapper {
//
//    @Mapping(target = "movie.id", source = "dto.movieId")
//    MovieAnnouncement toMovieAnnouncement(MovieAnnouncementDto dto);
//
//    MovieAnnouncementDto toMovieAnnouncementDto(MovieAnnouncement movieAnnouncement);
//}
