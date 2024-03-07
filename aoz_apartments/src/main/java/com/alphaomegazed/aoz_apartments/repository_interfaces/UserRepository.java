package com.alphaomegazed.aoz_apartments.repository_interfaces;

import com.alphaomegazed.aoz_apartments.model.UserModel; //to be accessed
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional; //this is part of the java.util package, but it needs to be included
import org.springframework.context.annotation.Profile;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    // Used by UserDetailService to define wether or not the user exists
    Optional<UserModel> findByUsername(String username);

    Boolean existsByUsername(String username);
}
