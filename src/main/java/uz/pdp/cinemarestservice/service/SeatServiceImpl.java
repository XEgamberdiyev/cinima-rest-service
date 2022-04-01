package uz.pdp.cinemarestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.cinemarestservice.dtos.SeatDto;
import uz.pdp.cinemarestservice.model.Seat;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.projection.AvailableProjection;
import uz.pdp.cinemarestservice.repository.PriceCategoryRepository;
import uz.pdp.cinemarestservice.repository.RowRepository;
import uz.pdp.cinemarestservice.repository.SeatRepository;
import uz.pdp.cinemarestservice.service.interfaces.SeatInterface;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatInterface {

    private final SeatRepository seatRepository;
    private final RowRepository rowRepository;
    private final PriceCategoryRepository priceCategoryRepository;


    @Override
    public ApiResponse getAllSeatsByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page -1, size);
        Page<Seat> seats = seatRepository.findAll(pageable);
        if (seats.getSize() == 0){
            return new ApiResponse("List empty!!!", false);
        }
        return new ApiResponse("Success!",true,seats);
    }

    @Override
    public ApiResponse getAllSeats() {
        List<Seat> seatList = seatRepository.findAll();
        if (seatList.size() == 0){
            return new ApiResponse("Seat not found!!!", false);
        }
        return new ApiResponse("Success!",true, seatList);
    }

    @Override
    public ApiResponse getSeatByRowId(UUID rowId) {
        List<Seat> seatList =  seatRepository.findByRowId(rowId);
        if (seatList.size() == 0){
            return new ApiResponse("Seat not found!!!", false);
        }
        return new ApiResponse("Success", true, seatList);
    }

    @Override
    public ApiResponse getSeatByHallId(UUID halId) {
        Optional<Seat> optionalSeat = seatRepository.findByHallId(halId);
        return optionalSeat.map(seat -> new ApiResponse("Success!", true, seat)).orElseGet(() -> new ApiResponse("Seat not found!!!", false));
    }

    @Override
    public ApiResponse getSeatById(UUID seatId) {
        Optional<Seat> optionalSeat = seatRepository.findById(seatId);
        return optionalSeat.map(seat -> new ApiResponse("Success!", true, seat)).orElseGet(() -> new ApiResponse("Seat not found!!!", false));
    }

    @Override
    public ApiResponse addSeat(SeatDto seatDto) {
       try {
           Seat newSeat = new Seat();
           newSeat.setNumber(seatDto.getNumber());
           newSeat.setRow(rowRepository.getById(seatDto.getRowId()));
           newSeat.setPriceCategories(priceCategoryRepository.getById(seatDto.getPriceCategoryId()));
           Seat saveSeat = seatRepository.save(newSeat);
           return new ApiResponse("Success!", true, saveSeat);
       } catch (Exception e){
           return new ApiResponse("Error", false);
       }
    }

    @Override
    public ApiResponse editSeat(UUID seatId, SeatDto seatDto) {
        Optional<Seat> optionalSeat = seatRepository.findById(seatId);
        if (!optionalSeat.isPresent()) {
            return new ApiResponse("Seat not found!!!", false);
        }
        try {
            Seat editSeat = optionalSeat.get();
            editSeat.setNumber(seatDto.getNumber());
            editSeat.setRow(rowRepository.getById(seatDto.getRowId()));
            editSeat.setPriceCategories(priceCategoryRepository.getById(seatDto.getPriceCategoryId()));
            Seat newSeat = seatRepository.save(editSeat);
            return new ApiResponse("Success!", true, newSeat);
        } catch (Exception e){
            return new ApiResponse("Error", false);
        }
    }

    @Override
    public ApiResponse deleteSeat(UUID seatId) {
        Optional<Seat> optionalSeat = seatRepository.findById(seatId);
        if (!optionalSeat.isPresent()) {
            return new ApiResponse("Seat not found!!!", false);
        }
        try {
            Seat deleteSeat = optionalSeat.get();
            seatRepository.delete(deleteSeat);
            return new ApiResponse("Success!", true);
        } catch (Exception e){
            return new ApiResponse("Error!!!", false);
        }
    }


    public ApiResponse getAvailableSeatsByMovieSessionId(UUID movieSessionId) {
        List<AvailableProjection> availableSeats = seatRepository.findAvailableSeatsBySessionId(movieSessionId);
        if (availableSeats.isEmpty()){
            return new ApiResponse("Not Found!!!", false);
        }
        return new ApiResponse("success", true, availableSeats);
    }
}
