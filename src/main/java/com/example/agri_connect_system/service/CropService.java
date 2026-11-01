package com.example.agri_connect_system.service;

import com.example.agri_connect_system.entity.Crop;
import com.example.agri_connect_system.repository.CropRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class CropService {
    private final CropRepository cropRepository;

    public CropService(CropRepository cropRepository){
        this.cropRepository = cropRepository;
    }
    public String saveCrop(Crop crop){
        cropRepository.save(crop);
        return "Crop saved successfully";
    }
    public List<Crop> getAllCrops(){
        return cropRepository.findAll();
    }
    public Optional<Crop> getCropById(UUID id){
        return cropRepository.findById(id);
    }
    public String updateCrop(UUID id, Crop updated){
        Optional<Crop> existing = cropRepository.findById(id);
        if (existing.isEmpty()){
            return "The crop with that id does not exist";
    }
    Crop crop = existing.get();
    crop.setName(updated.getName());
    cropRepository.save(crop);
    return "Crop updated successfully";
}
public String deleteCrop(UUID id){
    if (cropRepository.existsById(id)){
            return "Crop deleted successfully";
    }
        cropRepository.deleteById(id);
        return "The crop with that id does not exist";
    
    }
}


