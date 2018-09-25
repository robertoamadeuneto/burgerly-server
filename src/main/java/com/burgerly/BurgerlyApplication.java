package com.burgerly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class responsible for the startup of the Spring Boot application.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
@SpringBootApplication
public class BurgerlyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BurgerlyApplication.class, args);
    }
}