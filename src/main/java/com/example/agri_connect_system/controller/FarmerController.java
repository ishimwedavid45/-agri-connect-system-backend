package com.example.agri_connect_system.controller;

import com.example.agri_connect_system.entity.Farmer;
import com.example.agri_connect_system.service.FarmerService;   
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    private final FarmerService farmerService;

    public FarmerController(FarmerService farmerService){
        this.farmerService = farmerService;
    }
    @PostMapping("/save")
    public ResponseEntity<String> saveFarmer(@RequestBody Farmer farmer){
        String msg = farmerService.saveFarmer(farmer);
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Farmer>> getAllFarmers(){
        return ResponseEntity.ok(farmerService.getAllFarmers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getFarmerById(@PathVariable UUID id){
        Optional<Farmer> farmer = farmerService.getFarmerById(id);
        if(farmer.isEmpty()){
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The farmer with that id doesn't exist");
        }else{
            return ResponseEntity.ok(farmer.get());
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFarmer(@PathVariable UUID id, @RequestBody Farmer farmer){
        String msg = farmerService.updateFarmer(id, farmer);
        if(msg.equals("The farmer with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }else{
            return ResponseEntity.ok(msg);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFarmer(@PathVariable UUID id){
        String msg = farmerService.deleteFarmer(id);
        if(msg.equals("The farmer with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }else{
            return ResponseEntity.ok(msg);
        }
    }
}
