package com.example.agri_connect_system.service;

import com.example.agri_connect_system.entity.Cooperative;
import com.example.agri_connect_system.repository.CooperativeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class CooperativeService {

    private final CooperativeRepository cooperativeRepository;

    public CooperativeService(CooperativeRepository cooperativeRepository) {
        this.cooperativeRepository = cooperativeRepository;
    }
    public String saveCooperative(Cooperative cooperative){
        cooperativeRepository.save(cooperative);
        return "Cooperative saved successfully";
    }
    public List<Cooperative> getAllCooperatives(){
        return cooperativeRepository.findAll();
    }
    public Optional<Cooperative> getCooperativeById(UUID id){
        return cooperativeRepository.findById(id);
    }
    public String updateCooperative(UUID id, Cooperative updated){
        Optional<Cooperative> existing = cooperativeRepository.findById(id);
        if (existing.isEmpty()){
            return "The cooperative with that id does not exist";
        }
        Cooperative cooperative = existing.get();
        cooperative.setCoopNumber(updated.getCoopNumber());
        cooperative.setName(updated.getName());
        cooperativeRepository.save(cooperative);
        return "Cooperative updated successfully";
    }
    public String deleteCooperative(UUID id){
        if (cooperativeRepository.existsById(id)){
            return "Cooperative with that id does not exist";
        }
        cooperativeRepository.deleteById(id);
        return "Cooperative deleted successfully";
        }
    }


