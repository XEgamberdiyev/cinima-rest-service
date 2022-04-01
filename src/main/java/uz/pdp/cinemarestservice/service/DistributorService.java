package uz.pdp.cinemarestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.cinemarestservice.model.Distributor;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.repository.DistributorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class DistributorService {

    private final DistributorRepository distributorRepository;

    public ApiResponse getAllDistributors(){
        List<Distributor> distributorList = distributorRepository.findAll();
        if (distributorList.size() == 0){
            return new ApiResponse("List bosh",false);
        }
        return new ApiResponse("Success!",true, distributorList);
    }

    public ApiResponse getDistributors(UUID id){
      Optional<Distributor> optionalDistributor = distributorRepository.findById(id);
      if (!optionalDistributor.isPresent()){
          return new ApiResponse("Distributor not found",false);
      }
      return new ApiResponse("Success!",true, optionalDistributor.get());
    }

    public ApiResponse addDistributor(Distributor dist){
        boolean exists = distributorRepository.existsByName(dist.getName());
        if (exists){
            return new ApiResponse("Already exists!",false);
        }
        Distributor distributor = new Distributor();
        distributor.setDistributor(dist.getDistributor());
        distributor.setName(dist.getName());
        Distributor save = distributorRepository.save(distributor);
        return new ApiResponse("Successfully added!",true,save);
    }

    public ApiResponse editDistributor(UUID id, Distributor dist){
        Optional<Distributor> optionalDistributor = distributorRepository.findById(id);
        if (!optionalDistributor.isPresent()){
            return new ApiResponse("Distributor not found",false);
        }
        try {
           Distributor editingDistributor = optionalDistributor.get();
           editingDistributor.setName(dist.getName());
           editingDistributor.setDistributor(dist.getDistributor());
           Distributor save = distributorRepository.save(editingDistributor);
           return new ApiResponse("Successfully edited!",true,save);
        } catch (Exception e){
            return new ApiResponse("Error! Maybe distributor already exists!",false);
        }
    }

    public ApiResponse deleteDistributor(UUID id){
        Optional<Distributor> optionalDistributor = distributorRepository.findById(id);
        if (!optionalDistributor.isPresent()){
            return new ApiResponse("Distributor not found",false);
        }
        try {
            distributorRepository.deleteById(id);
            return new ApiResponse("Successfully deleted",true);
        } catch (Exception e){
            return new ApiResponse("Distributor not found",false);
        }
    }
}
