package com.example.agri_connect_system.entity;

import com.example.agri_connect_system.enums.FarmVisitStatus;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "farm_visit")
public class FarmVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate visitDate;

    @Enumerated(EnumType.STRING)
    private FarmVisitStatus status;

    private String purpose;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

    @ManyToOne
    @JoinColumn(name = "agronomist_id")
    private Agronomist agronomist;

    public FarmVisit() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public LocalDate getVisitDate() { return visitDate; }
    public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }

    public FarmVisitStatus getStatus() { return status; }
    public void setStatus(FarmVisitStatus status) { this.status = status; }

    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }

    public Farmer getFarmer() { return farmer; }
    public void setFarmer(Farmer farmer) { this.farmer = farmer; }

    public Agronomist getAgronomist() { return agronomist; }
    public void setAgronomist(Agronomist agronomist) { this.agronomist = agronomist; }
}
