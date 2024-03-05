package main.java.com.alphaomegazed.aoz_apartments.model;

import javax.persistence.*;

public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String address;

    private string getName(String name) {

        return name;
    }

    private string getAddress(String address) {

        return address;
    }

    private long getId(Long id) {

        return id;
    }
}
