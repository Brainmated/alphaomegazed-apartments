package com.alphaomegazed.aoz_apartments.repository_interfaces;

import com.alphaomegazed.aoz_apartments.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    List<Apartment> findByAddress(String address);

    List<Apartment> findByArea(double area);

    List<Apartment> findByMonthlyRent(double monthlyRent);

    // maybe another method to search by number of rooms

}
