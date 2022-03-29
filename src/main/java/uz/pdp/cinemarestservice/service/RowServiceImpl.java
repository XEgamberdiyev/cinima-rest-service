package uz.pdp.cinemarestservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarestservice.dto.RowDto;
import uz.pdp.cinemarestservice.model.Hall;
import uz.pdp.cinemarestservice.model.Row;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;
import uz.pdp.cinemarestservice.repository.HallRepo;
import uz.pdp.cinemarestservice.repository.RowRepo;
import uz.pdp.cinemarestservice.service.interfaces.RowService;

import java.util.List;
import java.util.Optional;

@Service
public class RowServiceImpl implements RowService {
    @Autowired
    RowRepo rowRepo;
    @Autowired
    HallRepo hallRepo;


    @Override
    public ApiResponse getAllRows() {
        List<Row> rows = rowRepo.findAll();
        if (rows.size() != 0) {
            return new ApiResponse("Success", true, rows);
        }
        return new ApiResponse("Row is not found", false, null);
    }

    @Override
    public ApiResponse getRowById(Integer id) {
        Optional<Row> optionalRow = rowRepo.findById(id);
        if (optionalRow.isPresent()) {
            Row row = optionalRow.get();
            return new ApiResponse("Success", true, row);
        }
        return new ApiResponse("Row is not found", false, null);
    }

    @Override
    public ApiResponse addAndAddRow(RowDto rowDto, Integer id) {
        if (id != null) {
            Optional<Row> optionalRow = rowRepo.findById(id);
            if (optionalRow.isPresent()) {
                Row row1 = optionalRow.get();
                row1.setName(rowDto.getName());
                Optional<Hall> optionalHall = hallRepo.findById(rowDto.getHallId());
                if (!optionalHall.isPresent()) {
                    return new ApiResponse("Hall is not found", false, null);
                }
                Hall hall = optionalHall.get();
                row1.setHall(hall);
                Row save = rowRepo.save(row1);
                return new ApiResponse("Success", true, save);
            }
        }

        Row row = new Row();
        row.setName(rowDto.getName());
        Optional<Hall> optionalHall = hallRepo.findById(rowDto.getHallId());
        if (!optionalHall.isPresent()) {
            return new ApiResponse("Hall is not found", false, null);
        }
        Hall hall = optionalHall.get();
        row.setHall(hall);
        Row save = rowRepo.save(row);
        return new ApiResponse("Success", true, save);
    }

    @Override
    public ApiResponse deleteRow(Integer id) {
        Optional<Row> optionalRow = rowRepo.findById(id);
        if (!optionalRow.isPresent()) {
            return new ApiResponse("Row is not found", false);
        }
        Row row = optionalRow.get();
        rowRepo.delete(row);
        return new ApiResponse("Success", true);
    }
}
