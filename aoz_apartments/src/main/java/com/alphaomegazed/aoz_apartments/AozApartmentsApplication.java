package com.alphaomegazed.aoz_apartments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.alphaomegazed")
@EntityScan("com.alphaomegazed.aoz_apartments.model")
@EnableJpaRepositories("com.alphaomegazed.aoz_apartments.repository_interfaces")
public class AozApartmentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AozApartmentsApplication.class, args);
	}

}
