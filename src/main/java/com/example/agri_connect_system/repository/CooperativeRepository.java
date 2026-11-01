package com.example.agri_connect_system.repository;

import com.example.agri_connect_system.entity.Agronomist;
import com.example.agri_connect_system.entity.Cooperative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CooperativeRepository extends JpaRepository<Cooperative, UUID> {

    @Query("SELECT c FROM Cooperative c WHERE NOT EXISTS (SELECT a FROM Agronomist a WHERE a.cooperative = c) ORDER BY c.coopNumber ASC")
    List<Cooperative> findFreeCooperatives();

    Optional<Cooperative> findByCoopNumber(int coopNumber);
}
