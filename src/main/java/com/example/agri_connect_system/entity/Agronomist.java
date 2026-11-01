package com.example.agri_connect_system.entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "agronomist")
public class Agronomist {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName; 
    private String phone;
    
    @ManyToOne
    @JoinColumn(name = "cooperative_id",nullable = true)
    private Cooperative cooperative;
    @ManyToMany
    @JoinTable(
        name = "agronomist_crop",
        joinColumns = @JoinColumn(name = "agronomist_id"),
        inverseJoinColumns = @JoinColumn(name = "crop_id")
    )
    private List<Crop> crops = new ArrayList<>();
    public Agronomist() {}
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;    }
        public String getLastName() {return lastName;}
        public void setLastName(String lastName) {this.lastName = lastName;}
        public String getPhone() {return phone;}
        public void setPhone(String phone) {this.phone = phone;}
        public Cooperative getCooperative(){return cooperative;}
        public void setCoopearative(Cooperative cooperative){this.cooperative = cooperative;}
         public List<Crop>getCrops(){return crops;}
         public void setCrop(List<Crop>crops){this.crops = crops;}

    public void setCooperative(Cooperative cooperative) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    }

