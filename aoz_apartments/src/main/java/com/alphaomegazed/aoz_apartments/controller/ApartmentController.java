package com.alphaomegazed.aoz_apartments.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.alphaomegazed.aoz_apartments.model.Apartment;
import com.alphaomegazed.aoz_apartments.service.ApartmentService;
import java.util.List;

import com.alphaomegazed.aoz_apartments.dto.RoomUpdateDto;
import com.alphaomegazed.aoz_apartments.exception.ResourceNotFoundException;

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
     * #API Endpoints ----------------------------------
     * 
     * #Endpoint to create new apartments.
     * #Takes an 'Apartment' object within the request body.
     * #Return said object.
     */
    @PostMapping("/apartments/")
    public ResponseEntity<?> createApartment(@RequestBody Apartment apartment) {
        Apartment createdApartment = apartmentService.createApartment(apartment);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Apartment with id " + createdApartment.getId() + " successfully created.");
    }

    /*
     * #Retrieves apartment from unique identifier.
     * #Updates apartment to include rooms.
     */
    @PostMapping("/apartments/{id}/rooms")
    public Apartment addRoomsToApartment(@PathVariable Long id, @RequestBody List<RoomUpdateDto> roomUpdates)
            throws ResourceNotFoundException {
        return apartmentService.addRoomsToApartment(id, roomUpdates);
    }

    /*
     * #Retrieves apartment from unique identifier.
     * #Returns ResponseEntity containing the aparment object or a 404.
     */
    @GetMapping("/apartments/{id}")
    public ResponseEntity<Apartment> getApartmentById(@PathVariable Long id) {
        return apartmentService.getApartmentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*
     * #Updates an apartment based on its id.
     * #Returns the updated object to the DB.
     */
    @PutMapping("/apartments/{id}")
    public ResponseEntity<?> updateApartment(@PathVariable Long id, @RequestBody Apartment apartment)
            throws ResourceNotFoundException {
        Apartment updatedApartment = apartmentService.updateApartment(id, apartment);
        return ResponseEntity.ok("Apartment with id " + updatedApartment.getId() + " successfully updated.");
    }

    /*
     * #Deletes an apartment based on its id.
     * #Doesnt return content, instead, it removes the specified object from the DB.
     */
    @DeleteMapping("/apartments/{id}")
    public ResponseEntity<?> deleteApartment(@PathVariable Long id) throws ResourceNotFoundException {
        apartmentService.deleteApartment(id);
        return ResponseEntity.ok("Apartment with id " + id + " successfully deleted.");
    }

    /*
     * #Lists all apartments.
     */
    @GetMapping("/apartments")
    public List<Apartment> listApartments() {
        return apartmentService.listApartments();
    }
}
