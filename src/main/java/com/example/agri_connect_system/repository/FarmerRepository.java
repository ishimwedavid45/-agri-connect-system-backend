package com.example.agri_connect_system.repository;

import com.example.agri_connect_system.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, UUID> {

    List<Farmer> findByDistrictIgnoreCaseOrderByLastNameAsc(String district);

    @Query("SELECT DISTINCT v.farmer FROM FarmVisit v WHERE v.agronomist.id = :agronomistId")
    List<Farmer> findFarmersByAgronomistId(@Param("agronomistId") UUID agronomistId);

    Optional<Farmer> findByFarmerCode(String farmerCode);
}
