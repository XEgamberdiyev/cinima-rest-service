package uz.pdp.cinemarestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.cinemarestservice.model.Hall;
import uz.pdp.cinemarestservice.model.Row;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.projection.RowProjection;
import uz.pdp.cinemarestservice.repository.HallRepository;
import uz.pdp.cinemarestservice.repository.RowRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RowService {

    private final RowRepository rowRepository;
    private final HallRepository hallRepository;


    public ApiResponse getAllRows(){
        List<Row> rows = rowRepository.findAll();
        if (rows.size() == 0){
            return new ApiResponse("Rows empty!!!", false);
        }
        return new ApiResponse("Success!",true, rows);
    }


    public ApiResponse getRowById(UUID id){
        Optional<Row> optionalRow = rowRepository.findById(id);
        if (!optionalRow.isPresent()) {
            return new ApiResponse("Row not found!!!", false);
        }
        Row row = optionalRow.get();
        return new ApiResponse("Success",true, row);
    }


    public ApiResponse addRow(UUID hall_Id, Row row) {
        Optional<Hall> optionalHall = hallRepository.findById(hall_Id);
        if (!optionalHall.isPresent()) {
            return new ApiResponse("Hall not found!!!", false);
        }
        try {
            Row saveRow = rowRepository.save(new Row(row.getNumber(), optionalHall.get()));
            return new ApiResponse("Success!", true, saveRow);
        } catch (Exception e) {
            return new ApiResponse("Error! Maybe row already exists!!", false);
        }
    }


    public ApiResponse editRow(UUID hall_id, UUID row_id, Row row){
        Optional<Hall> optionalHall = hallRepository.findById(hall_id);
        if (!optionalHall.isPresent()) {
            return new ApiResponse("Row not found!!!", false);
        }
        Optional<Row> optionalRow = rowRepository.findById(row_id);
        if (!optionalRow.isPresent()) {
            return new ApiResponse("Row not found!!!", false);
        }
        try {
            Row editRow = optionalRow.get();
            editRow.setNumber(row.getNumber());
            Row newRow = rowRepository.save(editRow);
            return new ApiResponse("Success!",true, newRow);
        } catch (Exception e) {
            return new ApiResponse("Error! Maybe row already exists!!", false);
        }
    }


    public ApiResponse deleteRow(UUID row_id){
        Optional<Row> optionalRow = rowRepository.findById(row_id);
        if (!optionalRow.isPresent()) {
            return new ApiResponse("Row not found!!!", false);
        }
        try {
            hallRepository.deleteById(optionalRow.get().getId());
            return new ApiResponse("Successfully deleted!", true);
        }  catch (Exception e) {
            return new ApiResponse("Error!!!", false);
        }
    }


    public ApiResponse getRowByHallId(UUID id){
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (!optionalHall.isPresent()) {
            return new ApiResponse("Hall not found!!!", false);
        }
        List<RowProjection> rowProjections = rowRepository.rowPrListRepos(id);
        if (rowProjections.size() == 0){
            return new ApiResponse("List empty!!!", false);
        }
        return new ApiResponse("Success", true, rowProjections);
    }
}
