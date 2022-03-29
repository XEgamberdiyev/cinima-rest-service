package uz.pdp.cinemarestservice.service.interfaces;

import org.springframework.stereotype.Service;
import uz.pdp.cinemarestservice.dto.RowDto;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;

@Service
public interface RowService {

    public ApiResponse getAllRows();

    public ApiResponse getRowById(Integer id);

    public  ApiResponse addAndAddRow(RowDto rowDto, Integer id);

    public ApiResponse deleteRow(Integer id);

}
