package com.alphaomegazed.aoz_apartments.model;

import jakarta.persistence.*;

import java.util.Map;

@Entity
@Table(name = "apartments")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Picture", nullable = false)
    private String picture;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "area", nullable = false)
    private double area;
    @Column(name = "monthlyRent", nullable = false)
    private double monthlyRent;

    @ElementCollection
    @CollectionTable(name = "apartment_rooms", joinColumns = @JoinColumn(name = "apartment_id"))
    @MapKeyColumn(name = "room_type")
    @Column(name = "count")
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
