package com.example.agri_connect_system.service;

import com.example.agri_connect_system.entity.FarmVisit;
import com.example.agri_connect_system.entity.Recommendation;
import com.example.agri_connect_system.enums.FarmVisitStatus;
import com.example.agri_connect_system.repository.FarmVisitRepository;
import com.example.agri_connect_system.repository.RecommendationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final FarmVisitRepository farmVisitRepository;

    public RecommendationService(RecommendationRepository recommendationRepository, FarmVisitRepository farmVisitRepository) {
        this.recommendationRepository = recommendationRepository;
        this.farmVisitRepository = farmVisitRepository;
    }

    public String saveRecommendation(UUID farmVisitId, Recommendation recommendation) {
        Optional<FarmVisit> visitOpt = farmVisitRepository.findById(farmVisitId);
        if (visitOpt.isEmpty()) {
            return "The farm visit with that id does not exist";
        }

        FarmVisit visit = visitOpt.get();
        if (visit.getStatus() != FarmVisitStatus.VISITED) {
            return "Recommendations are only for VISITED farm visits";
        }

        recommendation.setIssuedDate(LocalDate.now());
        recommendation.setFarmVisit(visit);
        recommendationRepository.save(recommendation);
        return "Recommendation saved successfully";
    }

    public List<Recommendation> getRecommendationsByFarmer(String farmerCode) {
        return recommendationRepository.findByFarmerCode(farmerCode);
    }

    public String deleteRecommendation(UUID id) {
        if (!recommendationRepository.existsById(id)) {
            return "The recommendation with that id does not exist";
        }
        recommendationRepository.deleteById(id);
        return "Recommendation deleted successfully";
    }
}
