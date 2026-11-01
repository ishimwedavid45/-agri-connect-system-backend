package com.example.agri_connect_system.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "farmer")
public class Farmer {
@Id 
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;

private String firstName;
private String lastName;

@Column (unique = true, nullable = false)
private String farmerCode;

private String phone;
private String district;

public Farmer() {}
 public UUID getId() {
    return id;}
    public void setId(UUID id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFarmerCode() {
        return farmerCode;
    }
    public void setFarmerCode(String farmerCode) {
        this.farmerCode = farmerCode;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }}