package uz.pdp.cinemarestservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarestservice.model.Seat;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;
import uz.pdp.cinemarestservice.projection.AvailableSeatsProjection;
import uz.pdp.cinemarestservice.repository.PriceCategoryRepo;
import uz.pdp.cinemarestservice.repository.SeatRepo;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    SeatRepo seatRepo;

    @Autowired
    PriceCategoryRepo priceCategoryRepo;

    public ApiResponse getAllSeats(){
        List<Seat> all = seatRepo.findAll();
        if (all!=null) {
            return new ApiResponse("Success", true, all);
        }
        return new ApiResponse("Not found", false, null);
    }

    public ApiResponse getSeatById(Integer id){
        Optional<Seat> optionalSeat = seatRepo.findById(id);
        if (optionalSeat.isPresent()) {
            Seat seat = optionalSeat.get();
            return new ApiResponse("Success", true, seat);
        }
        return new ApiResponse("Not found", false, null);
    }



    public HttpEntity<?> getAvailableSeatsBySessionId(Integer movieSessionId) {
        List<AvailableSeatsProjection> availableSeatsBySessionId =
                seatRepo.getAvailableSeatsBySessionId(movieSessionId);
        return ResponseEntity.ok(new ApiResponse("success", true, availableSeatsBySessionId));
    }

//    public ApiResponse addSeat(){
//        PriceCotegory priceCategory = new PriceCotegory();
//        PriceCotegory priceCategory1 = seat.getPriceCategory();
//
//        priceCategory.setName(priceCategory1.getName());
//        priceCategory.setColor(priceCategory1.getColor());
//        priceCategory.setAdditionalFeeInPercent(priceCategory1.getAdditionalFeeInPercent());
//        priceCategory.setSeats(priceCategory1.getSeats());
//        PriceCotegory savePriceCategory = priceCategoryRepo.save(priceCategory);
//
//        Seat seat1 = new Seat();
//        seat1.setRow(seat.getRow());
//        seat1.setPriceCategory(savePriceCategory);
//        Seat save = seatRepo.save(seat1);
//
//        if (save!=null) {
//
//        }
//    }

}
