package com.example.agri_connect_system.controller;

import com.example.agri_connect_system.entity.Recommendation;
import com.example.agri_connect_system.service.RecommendationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveRecommendation(
            @RequestParam UUID farmVisitId,
            @RequestBody Recommendation recommendation) {
        String msg = recommendationService.saveRecommendation(farmVisitId, recommendation);
        if (msg.equals("The farm visit with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        } else if (msg.equals("Recommendations are only for VISITED farm visits")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(msg);
        }
    }

    @GetMapping("/farmer/{farmerCode}")
    public ResponseEntity<List<Recommendation>> getRecommendationsByFarmer(@PathVariable String farmerCode) {
        return ResponseEntity.ok(recommendationService.getRecommendationsByFarmer(farmerCode));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRecommendation(@PathVariable UUID id) {
        String msg = recommendationService.deleteRecommendation(id);
        if (msg.equals("The recommendation with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        } else {
            return ResponseEntity.ok(msg);
        }
    }
}
