package com.alphaomegazed.aoz_apartments.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alphaomegazed.aoz_apartments.model.Apartment;
import com.alphaomegazed.aoz_apartments.service.ApartmentService;
import java.util.List;

/* This class handles http request and responses and maps them.
 * It also validates incoming data and sends back http responses.
 */
@RestController
public class ApartmentController {

    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @PostMapping
    public Apartment createApartment(@RequestBody Apartment apartment) {
        return apartmentService.createApartment(apartment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apartment> getApartmentById(@PathVariable Long id) {
        return apartmentService.getApartmentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Apartment updateApartment(@PathVariable Long id, @RequestBody Apartment apartment) {
        return apartmentService.updateApartment(id, apartment);
    }

    @DeleteMapping("/{id}")
    public void deleteApartment(@PathVariable Long id) {
        apartmentService.deleteApartment(id);
    }

    @GetMapping
    public List<Apartment> listApartments() {
        return apartmentService.listApartments();
    }
}
