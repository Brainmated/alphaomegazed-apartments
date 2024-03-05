package main.java.com.alphaomegazed.aoz_apartments.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType; //included for identity strategy
import javax.persistence.GeneratedValue; //corrected import instead of javax.annotation

@Entity
@Table(name = "users") // create a table for a start
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // adding setters methods to have some returns
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
