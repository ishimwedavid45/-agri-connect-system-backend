package com.example.agri_connect_system.repository;

import com.example.agri_connect_system.entity.FarmVisit;
import com.example.agri_connect_system.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, UUID> {

    @Query("SELECT r FROM Recommendation r WHERE r.farmVisit.farmer.farmerCode = :farmerCode ORDER BY r.issuedDate DESC")
    List<Recommendation> findByFarmerCode(@Param("farmerCode") String farmerCode);

    boolean existsByFarmVisit(FarmVisit farmVisit);
}
