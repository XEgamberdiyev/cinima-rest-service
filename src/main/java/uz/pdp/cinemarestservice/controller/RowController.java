package uz.pdp.cinemarestservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarestservice.dto.RowDto;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;
import uz.pdp.cinemarestservice.service.RowServiceImpl;

@RestController
@RequestMapping("/api/row")
public class RowController {
    @Autowired
    RowServiceImpl rowService;

    @GetMapping
    public HttpEntity<?> getAllRows(){
        ApiResponse rows = rowService.getAllRows();
        return ResponseEntity.status(rows.isSuccess()?200:404).body(rows);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getRowById(@PathVariable Integer id){
        ApiResponse rowById = rowService.getRowById(id);
        return ResponseEntity.status(rowById.isSuccess()?200:404).body(rowById);
    }

    @PostMapping
    public HttpEntity<?> addRow(Integer id, @RequestBody RowDto rowDto){
        ApiResponse response = rowService.addAndAddRow(rowDto, id);
        return ResponseEntity.status(response.isSuccess()?200:404).body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateRow(@PathVariable Integer id, @RequestBody RowDto rowDto){
        ApiResponse response = rowService.addAndAddRow(rowDto, id);
        return ResponseEntity.status(response.isSuccess()?200:404).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteRow(@PathVariable Integer id){
        ApiResponse response = rowService.deleteRow(id);
        return ResponseEntity.status(response.isSuccess()?200:404).body(response);
    }
}
