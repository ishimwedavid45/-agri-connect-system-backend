package com.example.agri_connect_system.service;

import com.example.agri_connect_system.entity.FarmVisit;
import com.example.agri_connect_system.enums.FarmVisitStatus;
import com.example.agri_connect_system.repository.FarmVisitRepository;
import com.example.agri_connect_system.repository.RecommendationRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FarmVisitService {

    private final FarmVisitRepository farmVisitRepository;
    private final RecommendationRepository recommendationRepository;

    public FarmVisitService(FarmVisitRepository farmVisitRepository, RecommendationRepository recommendationRepository) {
        this.farmVisitRepository = farmVisitRepository;
        this.recommendationRepository = recommendationRepository;
    }

    public Page<FarmVisit> getFarmVisitPage(Pageable pageable) {
        return farmVisitRepository.findAll(pageable);
    }

    // Requirement A2 + D1 (Safe saving)
    public String saveFarmVisit(FarmVisit farmVisit) {
        // Rule 1: visitDate cannot be in the past
        if (farmVisit.getVisitDate() != null && farmVisit.getVisitDate().isBefore(LocalDate.now())) {
            return "Farm visit date cannot be in the past";
        }

        // Rule 2: Agronomist already has a non-CALLED_OFF visit on that date
        if (farmVisit.getAgronomist() != null && farmVisit.getVisitDate() != null) {
            boolean busy = farmVisitRepository.existsByAgronomistAndVisitDateAndStatusNot(
                    farmVisit.getAgronomist(),
                    farmVisit.getVisitDate(),
                    FarmVisitStatus.CALLED_OFF
            );
            if (busy) {
                return "Agronomist already has a farm visit on that date";
            }
        }

        // Rule 3: Always starts as REQUESTED
        farmVisit.setStatus(FarmVisitStatus.REQUESTED);
        farmVisitRepository.save(farmVisit);
        return "Farm visit saved successfully";
    }

    public List<FarmVisit> getAllFarmVisits() {
        return farmVisitRepository.findAll();
    }

    public Optional<FarmVisit> getFarmVisitById(UUID id) {
        return farmVisitRepository.findById(id);
    }

    // Requirement D2: General PUT update must NOT change the status
    public String updateFarmVisit(UUID id, FarmVisit updated) {
        Optional<FarmVisit> existing = farmVisitRepository.findById(id);
        if (existing.isEmpty()) {
            return "The farm visit with that id does not exist";
        }
        FarmVisit visit = existing.get();
        visit.setVisitDate(updated.getVisitDate());
        visit.setPurpose(updated.getPurpose());
        visit.setFarmer(updated.getFarmer());
        visit.setAgronomist(updated.getAgronomist());
        farmVisitRepository.save(visit);
        return "Farm visit updated successfully";
    }

    // Requirement D2: Status flow
    public String updateStatus(UUID id, FarmVisitStatus newStatus) {
        Optional<FarmVisit> existing = farmVisitRepository.findById(id);
        if (existing.isEmpty()) {
            return "The farm visit with that id does not exist";
        }

        FarmVisit visit = existing.get();
        FarmVisitStatus current = visit.getStatus();

        // Allowed status transitions
        boolean allowed = false;
        if (current == FarmVisitStatus.REQUESTED) {
            if (newStatus == FarmVisitStatus.PLANNED || newStatus == FarmVisitStatus.CALLED_OFF) {
                allowed = true;
            }
        } else if (current == FarmVisitStatus.PLANNED) {
            if (newStatus == FarmVisitStatus.VISITED || newStatus == FarmVisitStatus.CALLED_OFF) {
                allowed = true;
            }
        }

        if (!allowed) {
            return "Cannot change status from " + current + " to " + newStatus;
        }

        visit.setStatus(newStatus);
        farmVisitRepository.save(visit);
        return "Farm visit status updated successfully";
    }

    public String deleteFarmVisit(UUID id) {
        Optional<FarmVisit> existing = farmVisitRepository.findById(id);
        if (existing.isEmpty()) {
            return "The farm visit with that id does not exist";
        }
        FarmVisit visit = existing.get();
        if (recommendationRepository.existsByFarmVisit(visit)) {
            return "That farm visit has recommendations, delete them first";
        }
        farmVisitRepository.deleteById(id);
        return "Farm visit deleted successfully";
    }

    // Requirement B2: Visits by status
    public List<FarmVisit> getVisitsByStatus(FarmVisitStatus status) {
        return farmVisitRepository.findByStatusOrderByVisitDateAsc(status);
    }

    // Requirement B3: Visits between dates (inclusive)
    public List<FarmVisit> getVisitsBetween(LocalDate start, LocalDate end) {
        return farmVisitRepository.findByVisitDateBetweenOrderByVisitDateAsc(start, end);
    }

    // Requirement F1: Stats by status
    public List<Object[]> getStatsByStatus() {
        return farmVisitRepository.countFarmVisitsPerStatus();
    }

    // Requirement F2: Cancel day visits
    @Transactional
    public String cancelDayVisits(UUID agronomistId, LocalDate date) {
        int cancelled = farmVisitRepository.cancelDayVisits(agronomistId, date);
        return cancelled + " farm visits cancelled";
    }
}
