package com.alphaomegazed.aoz_apartments.service;

import com.alphaomegazed.aoz_apartments.dto.RoomUpdateDto;
import com.alphaomegazed.aoz_apartments.exception.ResourceNotFoundException;
import com.alphaomegazed.aoz_apartments.model.Apartment;
import com.alphaomegazed.aoz_apartments.repository_interfaces.ApartmentRepository;
import com.alphaomegazed.aoz_apartments.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

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

    public Apartment addRoomsToApartment(Long id, List<RoomUpdateDto> roomUpdates) throws ResourceNotFoundException {
        Apartment apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Apartment not found with id: " + id));

        Map<String, Integer> currentRooms = apartment.getRooms();
        for (RoomUpdateDto update : roomUpdates) {
            String roomType = update.getRoomType();
            Integer roomCount = update.getRoomCount();
            // Update the room count. If the room type doesn't exist, it will be added.
            currentRooms.put(roomType, roomCount);
        }
        apartment.setRooms(currentRooms); // Update the apartment with the new rooms map

        return apartmentRepository.save(apartment); // Save the updated apartment
    }

    // Logic to update an existing apartment
    public Apartment updateApartment(Long id, Apartment apartmentDetails) throws ResourceNotFoundException {
        Apartment apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Apartment not found with id: " + id));

        apartment.setPicture(apartmentDetails.getPicture());
        apartment.setAddress(apartmentDetails.getAddress());
        apartment.setArea(apartmentDetails.getArea());
        apartment.setMonthlyRent(apartmentDetails.getMonthlyRent());
        apartment.setRooms(apartmentDetails.getRooms());

        // Save the updated apartment
        return apartmentRepository.save(apartment);

    }

    // Logic to delete an apartment
    public void deleteApartment(Long id) throws ResourceNotFoundException {
        Apartment apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Apartment not found with id: " + id));

        apartmentRepository.delete(apartment);
    }

    // Logic to list apartments
    public List<Apartment> listApartments() {
        return apartmentRepository.findAll();
    }
}
