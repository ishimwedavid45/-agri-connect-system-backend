package com.example.agri_connect_system.service;

import com.example.agri_connect_system.entity.Agronomist;
import com.example.agri_connect_system.entity.Cooperative;
import com.example.agri_connect_system.entity.Crop;
import com.example.agri_connect_system.repository.AgronomistRepository;
import com.example.agri_connect_system.repository.CooperativeRepository;
import com.example.agri_connect_system.repository.CropRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service

public class AgronomistService {
    private final AgronomistRepository agronomistRepository;
    private final CropRepository cropRepository;
    private final CooperativeRepository cooperativeRepository;


    public AgronomistService(AgronomistRepository agronomistRepository, CropRepository cropRepository, CooperativeRepository cooperativeRepository) {
        this.agronomistRepository = agronomistRepository;
        this.cropRepository = cropRepository;
        this.cooperativeRepository = cooperativeRepository;
    }
    public String saveAgronomist(Agronomist agronomist){
        agronomistRepository.save(agronomist);
        return "Agronomist saved successfully";
    }
    public List<Agronomist> getAllAgronomists(){
        return agronomistRepository.findAll();
    }
    public Optional<Agronomist> getAgronomistById(UUID id){
        return agronomistRepository.findById(id);
    }
    public String updateAgronomist(UUID id, Agronomist updated){
        Optional<Agronomist> existing = agronomistRepository.findById(id);
        if (existing.isEmpty()){
            return "The agronomist with that id does not exist";
        }
        Agronomist agronomist = existing.get();
        agronomist.setFirstName(updated.getFirstName());
        agronomist.setLastName(updated.getLastName());
        agronomist.setPhone(updated.getPhone());
        agronomist.setCooperative(updated.getCooperative());
        agronomistRepository.save(agronomist);
        return "Agronomist updated successfully";
    }
    public String deleteAgronomist(UUID id){
        if (agronomistRepository.existsById(id)){
            return "The agronomist with that id does not exist";
        }
        agronomistRepository.deleteById(id);
        return "Agronomist deleted successfully";
    }
    public List<Agronomist> getAgronomistsByCropName(String name){
        return agronomistRepository.findByCropName(name);
    }
    public String addCropToAgronomist(UUID agronomistId, UUID cropId){
        Optional<Agronomist> agroOpt = agronomistRepository.findById(agronomistId);
        if (agroOpt.isEmpty()){
            return "The agronomist with that id does not exist";
        }
         Optional<Crop> cropOpt = cropRepository.findById(cropId);
        if (cropOpt.isEmpty()){
            return "That crop with does not exist";
    }
    Agronomist agronomist = agroOpt.get();
    Crop crop = cropOpt.get();

    for (Crop c : agronomist.getCrops()){
        if (c.getId().equals(cropId)){
            return " the agronomist already has that crop";
        }
    }
    agronomist.getCrops().add(crop);
        agronomistRepository.save(agronomist);
        return "Crop added to agronomist";

    }
    public String moveAgronomistToCooperative(UUID agronomistId, int coopNumber){
        Optional<Agronomist> agroOpt = agronomistRepository.findById(agronomistId);
        if (agroOpt.isEmpty()){
            return "The agronomist with that id does not exist";
        }
        Optional<Cooperative> coopOpt = cooperativeRepository.findByCoopNumber(coopNumber);
        if (coopOpt.isEmpty()){
            return "The cooperative with that coopNumber does not exist";
        }
        Cooperative newCoop = coopOpt.get();
        if (agronomistRepository.existsByCooperative(newCoop)){
            return "The cooperative is takenby another agronomist";
        }
        Agronomist agronomist = agroOpt.get();       
        agronomist.setCooperative(newCoop);
        agronomistRepository.save(agronomist);
        return "Cooperative changed successfully";
    }
}
