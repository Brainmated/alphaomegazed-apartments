package com.alphaomegazed.aoz_apartments.repository_interfaces;

import com.alphaomegazed.aoz_apartments.model.UserModel; //to be accessed
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional; //this is part of the java.util package, but it needs to be included

public interface UserRepository extends JpaRepository<UserModel, Long> {

    // Used by UserDetailService to define wether or not the user exists
    Optional<UserModel> findByUsername(String username);

    Boolean existsByUsername(String username);
}
