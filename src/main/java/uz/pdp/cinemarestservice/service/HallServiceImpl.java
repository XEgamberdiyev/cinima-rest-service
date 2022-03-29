package uz.pdp.cinemarestservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarestservice.model.Hall;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;
import uz.pdp.cinemarestservice.repository.HallRepo;
import uz.pdp.cinemarestservice.service.interfaces.HallService;

import java.util.List;
import java.util.Optional;

@Service
public class HallServiceImpl implements HallService {

    @Autowired
    HallRepo hallRepo;

    @Override
    public ApiResponse getAllHalls() {
        List<Hall> halls = hallRepo.findAll();
        if (halls.size() != 0){
            return new ApiResponse("Success", true, halls);
        }
        return new ApiResponse("Hall is not found", false, null);
    }

    @Override
    public ApiResponse getHallById(Integer id) {
        Optional<Hall> optionalHall = hallRepo.findById(id);
        if (optionalHall.isPresent()) {
            Hall hall = optionalHall.get();
            return new ApiResponse("Success", true, hall);
        }
        return new ApiResponse("Hall is not found", false, null);
    }

    @Override
    public ApiResponse addHall(Hall hall, Integer id) {
            Hall hall1 = new Hall();
        if (id != null){
            Optional<Hall> optionalHall = hallRepo.findById(id);
            if (optionalHall.isPresent()) {
                hall1 = optionalHall.get();
                hall1.setName(hall.getName());
                hall1.setVipAdditionalFeeInPercent(hall.getVipAdditionalFeeInPercent());
                Hall save = hallRepo.save(hall1);
                if (save != null) {
                    return new ApiResponse("Success", true, save);
                }
                return new ApiResponse("Hall is not found", false, null);

            }
        }
            hall1.setName(hall.getName());
            hall1.setVipAdditionalFeeInPercent(hall.getVipAdditionalFeeInPercent());
            Hall save = hallRepo.save(hall1);
            if (save != null) {
                return new ApiResponse("Success", true, save);
            }
            return new ApiResponse("Hall is not found", false, null);
    }

//    @Override
//    public ApiResponse updateHall(Integer id, Hall hall) {
//        return null;
//    }

    @Override
    public ApiResponse deleteHall(Integer id) {
        Optional<Hall> optionalHall = hallRepo.findById(id);
        if (optionalHall.isPresent()) {
            Hall hall = optionalHall.get();
            hallRepo.delete(hall);
                return new ApiResponse("Success", true);
        }
            return new ApiResponse("Hall is not found", false);
    }
}
