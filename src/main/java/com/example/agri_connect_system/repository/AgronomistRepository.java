package com.example.agri_connect_system.repository;

import com.example.agri_connect_system.entity.Agronomist;
import com.example.agri_connect_system.entity.Cooperative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AgronomistRepository extends JpaRepository<Agronomist, UUID> {

    @Query("SELECT a FROM Agronomist a JOIN a.crops c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Agronomist> findByCropName(@Param("name") String name);

    boolean existsByCooperative(Cooperative cooperative);
}
