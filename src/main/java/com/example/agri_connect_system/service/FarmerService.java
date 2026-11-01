package com.example.agri_connect_system.service;

import com.example.agri_connect_system.entity.Farmer;
import com.example.agri_connect_system.repository.AgronomistRepository;
import com.example.agri_connect_system.repository.FarmerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class FarmerService {
    private final FarmerRepository farmerRepository;
    private final AgronomistRepository agronomistRepository;

    public FarmerService(FarmerRepository farmerRepository, AgronomistRepository agronomistRepository) {
        this.farmerRepository = farmerRepository;
        this.agronomistRepository = agronomistRepository;
    }
    public String saveFarmer(Farmer farmer){
        farmerRepository.save(farmer);
        return "Farmer saved successfully";
    }
    public List<Farmer> getAllFarmers(){
        return farmerRepository.findAll();
    }
    public Optional<Farmer> getFarmerById(UUID id){
        return farmerRepository.findById(id);
    }
    public String updateFarmer(UUID id , Farmer updated){
        Optional<Farmer> existing = farmerRepository.findById(id);
        if (existing.isEmpty()){
            return "The farmer with that id does not exist";
        }
        Farmer farmer = existing.get();
        farmer.setFirstName(updated.getFirstName());
        farmer.setLastName(updated.getLastName());
        farmer.setFarmerCode(updated.getFarmerCode());
        farmer.setPhone(updated.getPhone());
        farmer.setDistrict(updated.getDistrict());    
        farmerRepository.save(farmer);
        return "Farmer updated successfully";
    }
    public String deleteFarmer(UUID id){
        if (farmerRepository.existsById(id)){
            return "The farmer with that id does not exist";
        }
        farmerRepository.deleteById(id);
        return "Farmer deleted successfully";
    }
    public List<Farmer> getFarmersByDistrict(String district){
        return farmerRepository.findByDistrictIgnoreCaseOrderByLastNameAsc(district);
    }
    public Optional<List<Farmer>> getFarmersByAgronomistId(UUID agronomistId){
        if (!agronomistRepository.existsById(agronomistId)){
            return Optional.empty();
        }
        return Optional.of(farmerRepository.findFarmersByAgronomistId(agronomistId));
    }


}
