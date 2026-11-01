package com.example.agri_connect_system.controller;

import com.example.agri_connect_system.entity.Agronomist;
import com.example.agri_connect_system.service.AgronomistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RestController
@RequestMapping("/api/agronomists")

public class AgronomistController {
    
    private final AgronomistService agronomistService;

    public AgronomistController(AgronomistService agronomistService){
        this.agronomistService = agronomistService;
    }
    @PostMapping("/save")
    public ResponseEntity<String> saveAgronomist(@RequestBody Agronomist agronomist){
        String msg = agronomistService.saveAgronomist(agronomist);
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Agronomist>> getAllAgronomists(){
        return ResponseEntity.ok(agronomistService.getAllAgronomists());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAgronomistById(@PathVariable UUID id){
        Optional<Agronomist> ag = agronomistService.getAgronomistById(id);
        if(ag.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The agronomist withthat id doesn't exist");
        }else{
            return ResponseEntity.ok(ag.get());
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAgronomist(@PathVariable UUID id, @RequestBody Agronomist agronomist){
        String msg = agronomistService.updateAgronomist(id, agronomist);
        if(msg.equals("The agronomist with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }else{
            return ResponseEntity.ok(msg);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAgronomist(@PathVariable UUID id){
        String msg = agronomistService.deleteAgronomist(id);
        if(msg.equals("The agronomist with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }else{
            return ResponseEntity.ok(msg);
        }
    }
 
}
