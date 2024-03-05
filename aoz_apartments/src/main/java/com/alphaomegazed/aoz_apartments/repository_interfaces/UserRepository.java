package main.java.com.alphaomegazed.aoz_apartments.repository_interfaces;

import main.java.com.alphaomegazed.aoz_apartments.model.User; //to be accessed
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional; //this is part of the java.util package, but it needs to be included

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}
