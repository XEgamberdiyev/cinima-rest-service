package uz.pdp.cinemarestservice.service.interfaces;

import org.springframework.stereotype.Service;
import uz.pdp.cinemarestservice.model.Hall;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;

@Service
public interface HallService {

    public ApiResponse getAllHalls();

    public ApiResponse getHallById(Integer id);

    public ApiResponse addHall(Hall hall, Integer id);

//    public ApiResponse updateHall(Integer id, Hall hall);

    public ApiResponse deleteHall(Integer id);

}
