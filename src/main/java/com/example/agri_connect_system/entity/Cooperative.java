package com.example.agri_connect_system.entity;
import jakarta.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "cooperative")
public class Cooperative {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int coopNumber;
    private String name;
    public Cooperative() {}
    public Cooperative(int coopNumber, String name){
        this.coopNumber = coopNumber;
        this.name = name;
    }
    public UUID getId(){return id;}

    public void setId(UUID id){this.id = id;}

    public int getCoopNumber(){return coopNumber;}
    
    public void setCoopNumber(int coopNumber){this.coopNumber = coopNumber;}
    
    public String getName(){return name;}
    
    public void setName(String name){this.name = name;}

}
