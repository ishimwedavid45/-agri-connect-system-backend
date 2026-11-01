package com.example.agri_connect_system.controller;

import com.example.agri_connect_system.entity.Crop;
import com.example.agri_connect_system.service.CropService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/crops")

public class CropController{

    private final CropService cropService;

    public CropController (CropService cropService){ this.cropService = cropService;}
    
    @PostMapping("/save")
    public ResponseEntity<String>saveCrop(@RequestBody Crop crop){
        String msg= cropService.saveCrop(crop);
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);

    }
    @GetMapping("/all")
    public ResponseEntity<List<Crop>>getAllCrops(){
        return ResponseEntity.ok(cropService.getAllCrops());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCropById(@PathVariable UUID id) {
    Optional<Crop> crop = cropService.getCropById(id);
    if(crop.isEmpty()){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The crop with that id doesn't exist");

    }else {
        return ResponseEntity.ok(crop.get());
    }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCrop(@PathVariable UUID id,@RequestBody Crop crop){
        String msg = cropService.updateCrop(id, crop);
    if(msg.equals("The crop with that id does not exist")){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }else{
        return ResponseEntity.ok(msg);
    }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCrop(@PathVariable UUID id ){
        String msg = cropService.deleteCrop(id);
        if (msg.equals("The crop with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }else{
            return ResponseEntity.ok(msg);
        }
    }
}