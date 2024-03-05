package main.java.com.alphaomegazed.aoz_apartments.model;

import javax.annotation.processing.Generated;

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
}
