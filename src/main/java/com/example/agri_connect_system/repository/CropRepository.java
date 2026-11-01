package com.example.agri_connect_system.repository;

import com.example.agri_connect_system.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CropRepository extends JpaRepository<Crop, UUID> {

    @Query("SELECT c FROM Crop c WHERE c.agronomists IS EMPTY")
    List<Crop> findUnusedCrops();
}
