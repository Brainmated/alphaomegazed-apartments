package com.alphaomegazed.aoz_apartments.repository_interfaces;

import com.alphaomegazed.aoz_apartments.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    // Will see what to do later

}
