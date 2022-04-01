package uz.pdp.cinemarestservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarestservice.model.Row;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.service.RowService;

import java.util.UUID;

@RestController
@RequestMapping("/api/row")
@RequiredArgsConstructor
public class RowController {

    private final RowService rowService;

    @GetMapping
    public HttpEntity<?> getAllRows(){
        ApiResponse apiResponse = rowService.getAllRows();
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 204).body(apiResponse);
    }

    @GetMapping("/{rowId}")
    public HttpEntity<?> getRowById(@PathVariable UUID rowId){
        ApiResponse apiResponse = rowService.getRowById(rowId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/getRowByHollId/{hallId}")
    public HttpEntity<?> getRowByHallId(@PathVariable UUID hallId){
        ApiResponse apiResponse = rowService.getRowByHallId(hallId);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PostMapping("/{hallId}")
    public HttpEntity<?> addRow(@PathVariable UUID hallId, @RequestBody Row row){
        ApiResponse apiResponse = rowService.addRow(hallId, row);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{hall_id}/{row_id}")
    public HttpEntity<?> editRow(@PathVariable UUID hall_id, @PathVariable UUID row_id, @RequestBody Row row){
        ApiResponse apiResponse = rowService.editRow(hall_id, row_id, row);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{row_id}")
    public HttpEntity<?> deleteRow(@PathVariable UUID row_id){
        ApiResponse apiResponse = rowService.deleteRow(row_id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 409).body(apiResponse);
    }
}
