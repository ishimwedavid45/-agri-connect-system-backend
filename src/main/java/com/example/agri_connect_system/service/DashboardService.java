package com.example.agri_connect_system.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.agri_connect_system.enums.FarmVisitStatus;
import com.example.agri_connect_system.repository.AgronomistRepository;
import com.example.agri_connect_system.repository.CooperativeRepository;
import com.example.agri_connect_system.repository.FarmVisitRepository;
import com.example.agri_connect_system.repository.FarmerRepository;
import com.example.agri_connect_system.repository.RecommendationRepository;

@Service

public class DashboardService {
    private final FarmerRepository farmerRepository;
    private final AgronomistRepository agronomistRepository;
    private final CooperativeRepository cooperativeRepository;
    private final FarmVisitRepository farmVisitRepository;
    private final RecommendationRepository recommendationRepository;

    public DashboardService(FarmerRepository farmerRepository, AgronomistRepository agronomistRepository, CooperativeRepository cooperativeRepository, FarmVisitRepository farmVisitRepository, RecommendationRepository recommendationRepository){
        this.farmerRepository = farmerRepository;
        this.agronomistRepository = agronomistRepository;
        this.cooperativeRepository = cooperativeRepository;
        this.farmVisitRepository = farmVisitRepository;
        this.recommendationRepository = recommendationRepository;
    }
    public Map<String, Long>getDashboardSummary(){
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("farmers", farmerRepository.count());
        summary.put("agronomists", agronomistRepository.count());
        summary.put("freeCooperatives", (long) cooperativeRepository.findFreeCooperatives().size());
        summary.put("visitsToday", farmVisitRepository.countByVisitDate(LocalDate.now()));
        summary.put("requested",farmVisitRepository.countByStatus(FarmVisitStatus.REQUESTED));
        summary.put("recommendations", recommendationRepository.count());

        return summary;
    }

}
