package uz.pdp.cinemarestservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarestservice.model.PriceCotegory;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;
import uz.pdp.cinemarestservice.service.PriceCategoryService;

@RestController
@RequestMapping("/api/priceCategory")
public class PriceCategoryController {

    @Autowired
    PriceCategoryService priceCategoryService;

    @GetMapping
    public ApiResponse getAllPriceCategories(){
       return priceCategoryService.getAllPriceCategory();
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getPriceCategoryById(@PathVariable Integer id){
        ApiResponse priceCategoryById = priceCategoryService.getPriceCategoryById(id);
        return ResponseEntity.status(priceCategoryById.isSuccess()? 200: 404).body(priceCategoryById);
    }

    @PostMapping
    public ApiResponse addPriceCategory(@RequestBody PriceCotegory priceCotegory){
        return priceCategoryService.addPriceCategory(priceCotegory);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deletePriceCategory(@PathVariable Integer id){
        return priceCategoryService.deletePriceCategory(id);
    }

    @PutMapping("/{id}")
    public ApiResponse updatePriceCategory(@PathVariable Integer id,
                                           @RequestBody PriceCotegory priceCotegory){
        return priceCategoryService.updatePriceCategory(id, priceCotegory);
    }

}
