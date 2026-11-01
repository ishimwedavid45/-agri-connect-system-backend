package com.example.agri_connect_system.controller;

import com.example.agri_connect_system.entity.FarmVisit;
import com.example.agri_connect_system.service.FarmVisitService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RestController
@RequestMapping("/api/farmvisits")
public class FarmVisitController {
    private final FarmVisitService farmVisitService;

    public FarmVisitController(FarmVisitService farmVisitService) {
        this.farmVisitService = farmVisitService;
    }
    @PostMapping("/save")
    public ResponseEntity<String> saveFarmVisit(@RequestBody FarmVisit farmVisit){
        String msg = farmVisitService.saveFarmVisit(farmVisit);
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }
    @GetMapping("/all")
    public ResponseEntity<List<FarmVisit>> getAllFarmVisits(){
        return ResponseEntity.ok(farmVisitService.getAllFarmVisits());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getFarmVisitById(@PathVariable UUID id){
        Optional<FarmVisit> visit = farmVisitService.getFarmVisitById(id);
        if(visit.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The farm visit with that id doesn't exist");
        }else{
            return ResponseEntity.ok(visit.get());
        }
    }
    @GetMapping("/page")
    public ResponseEntity<Page<FarmVisit>> getFarmVisitPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,@RequestParam(defaultValue = "visitDate,desc") String sort){
        String[]sortParts = sort.split(",");
        String property = sortParts[0];
        Sort.Direction direction = (sortParts.length > 1 && sortParts[1].equalsIgnoreCase("asc"))
        ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, property));
        return ResponseEntity.ok(farmVisitService.getFarmVisitPage(pageable));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFarmVisit(@PathVariable UUID id, @RequestBody FarmVisit farmVisit){
        String msg = farmVisitService.updateFarmVisit(id, farmVisit);
        if(msg.equals("The farm visit with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }else{
            return ResponseEntity.ok(msg);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFarmVisit(@PathVariable UUID id){
        String msg = farmVisitService.deleteFarmVisit(id);
        if(msg.equals("The farm visit with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
        else if(msg.equals("that farm visit has recommendations, delete them first")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(msg);
        }else{
            return ResponseEntity.ok(msg);
        }
    }
}
