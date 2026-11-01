package com.example.agri_connect_system.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recommendation")
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String practice;
    private String advice;
    private int followUpDays;
    private LocalDate issuedDate;

    @ManyToOne
    @JoinColumn(name = "farm_visit_id")
    private FarmVisit farmVisit;
     public Recommendation(){}
     public UUID getId(){return id;}
     public void setId(UUID id){this.id=id;}
     public String getPractice(){return practice;}
     public void setPractice(String practice){this.practice=practice;}
     public String getAdvice(){return advice;}
     public void setAdvice(String advice){this.advice= advice;}
     public int getFollowUpDays(){return followUpDays;}
     public void setFollwUpDays(int followUpDays){this.followUpDays=followUpDays;}
     public LocalDate getIssuedDate(){return issuedDate;}
     public void setIssuedDate(LocalDate issuedDate){
        this.issuedDate = issuedDate;
    }

     public FarmVisit getFarmVisit(){return farmVisit;}
     public void setFarmVisit(FarmVisit farmVisit){
        this.farmVisit = farmVisit;
    }

 

}
