package uz.pdp.cinemarestservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinemarestservice.model.PriceCotegory;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;
import uz.pdp.cinemarestservice.repository.PriceCategoryRepo;

import java.util.List;
import java.util.Optional;

@Service
public class PriceCategoryService {

    @Autowired
    PriceCategoryRepo priceCategoryRepo;

    public ApiResponse getAllPriceCategory() {
        List<PriceCotegory> all = priceCategoryRepo.findAll();
        if (all != null) {
            return new ApiResponse("Success", true, all);
        }
        return new ApiResponse("Not found", false, null);
    }

    public ApiResponse getPriceCategoryById(Integer id) {
        Optional<PriceCotegory> optionalPriceCotegory = priceCategoryRepo.findById(id);
        if (optionalPriceCotegory.isPresent()) {
            PriceCotegory priceCotegory = optionalPriceCotegory.get();
            return new ApiResponse("Success", true, priceCotegory);
        }
        return new ApiResponse("Not found", false, null);
    }

    public ApiResponse addPriceCategory(PriceCotegory priceCotegory1) {
        PriceCotegory priceCotegory = new PriceCotegory();
        priceCotegory.setName(priceCotegory1.getName());
        priceCotegory.setColor(priceCotegory1.getColor());
        priceCotegory.setAdditionalFeeInPercent(priceCotegory1.getAdditionalFeeInPercent());
        PriceCotegory save = priceCategoryRepo.save(priceCotegory);
        if (priceCotegory != null) {
            return new ApiResponse("Success", true, save);
        }
        return new ApiResponse("Not found", false, null);
    }

    public ApiResponse deletePriceCategory(Integer id){
        Optional<PriceCotegory> optionalPriceCotegory = priceCategoryRepo.findById(id);
        if (optionalPriceCotegory.isPresent()) {
            PriceCotegory priceCotegory = optionalPriceCotegory.get();
            priceCategoryRepo.delete(priceCotegory);
            return new ApiResponse("Success", true);
        }
        return new ApiResponse("Not found", false);
    }

    public ApiResponse updatePriceCategory(Integer id, PriceCotegory priceCotegory1){
        Optional<PriceCotegory> optionalPriceCotegory = priceCategoryRepo.findById(id);
        if (optionalPriceCotegory.isPresent()) {
            PriceCotegory priceCotegory = optionalPriceCotegory.get();
            priceCotegory.setName(priceCotegory1.getName());
            priceCotegory.setColor(priceCotegory1.getColor());
            priceCotegory.setAdditionalFeeInPercent(priceCotegory1.getAdditionalFeeInPercent());
            PriceCotegory save = priceCategoryRepo.save(priceCotegory);
            return new ApiResponse("Success", true, save);
        }
        return new ApiResponse("Not found", false, null);
    }

}
