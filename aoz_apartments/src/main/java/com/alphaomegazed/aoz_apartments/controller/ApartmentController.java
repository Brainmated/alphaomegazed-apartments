package com.alphaomegazed.aoz_apartments.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alphaomegazed.aoz_apartments.model.Apartment;
import com.alphaomegazed.aoz_apartments.service.ApartmentService;
import java.util.List;

/* This Rest Controller class handles http request and responses and maps them.
 * It also validates incoming data and sends back http responses.
 
 #Standout Variables
 'apartmentService' is the service layer that the controller uses to perform opeartions on apartment data.
 */
@RestController
public class ApartmentController {

    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    /*
    #API Endpoints ----------------------------------
     
    #Endpoint to create new apartments.
    #Takes an 'Apartment' object within the request body.
    #Return said object.
    */
    @PostMapping("/apartments")
    public Apartment createApartment(@RequestBody Apartment apartment) {
        return apartmentService.createApartment(apartment);
    }

    /*
    #Retrieves apartment from unique identifier.
    #Returns ResponseEntity containing the aparment object or a 404.
    */
    @GetMapping("/{id}")
    public ResponseEntity<Apartment> getApartmentById(@PathVariable Long id) {
        return apartmentService.getApartmentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*
    #Updates an apartment based on its id.
    #Returns the updated object to the DB.
    */
    @PutMapping("/{id}")
    public Apartment updateApartment(@PathVariable Long id, @RequestBody Apartment apartment) {
        return apartmentService.updateApartment(id, apartment);
    }

    /*
    #Deletes an apartment based on its id.
    #Doesnt return content, instead, it removes the specified object from the DB.
    */
    @DeleteMapping("/{id}")
    public void deleteApartment(@PathVariable Long id) {
        apartmentService.deleteApartment(id);
    }

    /*
    #Lists all apartments.
    */
    @GetMapping
    public List<Apartment> listApartments() {
        return apartmentService.listApartments();
    }
}
