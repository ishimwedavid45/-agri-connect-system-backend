package com.example.agri_connect_system.repository;

import com.example.agri_connect_system.entity.Agronomist;
import com.example.agri_connect_system.entity.FarmVisit;
import com.example.agri_connect_system.enums.FarmVisitStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface FarmVisitRepository extends JpaRepository<FarmVisit, UUID> {
    List<FarmVisit> findByStatusOrderByVisitDateAsc(FarmVisitStatus status);
    List<FarmVisit> findByVisitDateBetweenOrderByVisitDateAsc(LocalDate startDate, LocalDate endDate);

    boolean existsByAgronomistAndVisitDateAndStatusNot(Agronomist agronomist, LocalDate visitDate, FarmVisitStatus status);

    @Query("SELECT v.status, COUNT(v) FROM FarmVisit v GROUP BY v.status")
    List<Object[]> countFarmVisitsPerStatus();

    @Modifying
    @Query("UPDATE FarmVisit v SET v.status = com.example.agri_connect_system.enums.FarmVisitStatus.CALLED_OFF WHERE v.agronomist.id = :agronomistId AND v.visitDate = :date AND v.status != com.example.agri_connect_system.enums.FarmVisitStatus.VISITED")
    int cancelDayVisits(@Param("agronomistId") UUID agronomistId, @Param("date") LocalDate date);

    long countByVisitDate(LocalDate visitDate);
    long countByStatus(FarmVisitStatus status);
}
