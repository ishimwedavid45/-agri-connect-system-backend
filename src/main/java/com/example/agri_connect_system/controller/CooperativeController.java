package com.example.agri_connect_system.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.agri_connect_system.entity.Cooperative;
import com.example.agri_connect_system.service.CooperativeService;

@RestController
@RequestMapping("/api/cooperatives")

public class CooperativeController {

    private final CooperativeService cooperativeService;
    public CooperativeController(CooperativeService cooperativeService){
        this.cooperativeService = cooperativeService;
    }
    @PostMapping("/save")
    public ResponseEntity<String> saveCooperative(@RequestBody Cooperative cooperative){
        String msg = cooperativeService.saveCooperative(cooperative);
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Cooperative>> getAllCooperatives(){
        return ResponseEntity.ok(cooperativeService.getAllCooperatives());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCooperativeById(@PathVariable UUID id){
        Optional<Cooperative> coop = cooperativeService.getCooperativeById(id);
        if(coop.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The cooperative with that id doesn't exist");
        }else{
            return ResponseEntity.ok(coop.get());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCooperative(@PathVariable UUID id){
        String msg = cooperativeService.deleteCooperative(id);
        if(msg.equals("The cooperative with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }else{
            return ResponseEntity.ok(msg);
        }
    }
}
