package main.java.com.alphaomegazed.aoz_apartments.service;

import com.alphaomegazed.aoz_apartments.*;

import main.java.com.alphaomegazed.aoz_apartments.model.Apartment;
import main.java.com.alphaomegazed.aoz_apartments.repository_interfaces.ApartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    // Logic to save an apartment in the database
    public Apartment createApartment(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    // Logic to get the apartment based on its id
    public Optional<Apartment> getApartmentById(Long id) {
        return apartmentRepository.findById(id);
    }

    // Logic to update an existing apartment
    public Apartment updateApartment(Long id, Apartment apartmentDetails) {
        Apartment apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apartment not found."));

        apartment.setPicture(apartmentDetails.getPicture());
        apartment.setAddress(apartmentDetails.getAddress());
        apartment.setArea(apartmentDetails.getArea());
        apartment.setMonthlyRent(apartmentDetails.getMonthlyRent());
        apartment.setRooms(apartmentDetails.getRooms());

        // Save the updated apartment
        return apartmentRepository.save(apartment);

    }

    // Logic to delete an apartment
    public void deleteApartment(Long id) {
        Apartment apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apartment not found."));

        apartmentRepository.delete(apartment);
    }

    // Logic to list apartments
    public List<Apartment> listApartments() {
        return apartmentRepository.findAll();
    }
}
