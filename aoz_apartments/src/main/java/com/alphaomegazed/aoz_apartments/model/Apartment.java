package com.alphaomegazed.aoz_apartments.model;

import javax.persistence.*;

import java.util.Map;

@Entity
@Table(name = "apartment")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String picture;
    private String address;
    private double area;
    private double monthlyRent;

    @ElementCollection
    private Map<String, Integer> rooms;

    // Setters--------------------------------------
    public void setId(Long id) {
        this.id = id;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setMonthlyRent(double monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public void setRooms(Map<String, Integer> rooms) {
        this.rooms = rooms;
    }

    // Getters-----------------------------------------------
    public Long getId() {
        return id;
    }

    public String getPicture() {
        return picture;
    }

    public String getAddress() {
        return address;
    }

    public double getArea() {
        return area;
    }

    public double getMonthlyRent() {
        return monthlyRent;
    }

    @ElementCollection
    public Map<String, Integer> getRooms() {
        return rooms;
    }
}
