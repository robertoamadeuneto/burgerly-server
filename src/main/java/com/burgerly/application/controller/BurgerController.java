package com.burgerly.application.controller;

import com.burgerly.application.exception.ResourceNotFoundException;
import com.burgerly.domain.model.Burger;
import com.burgerly.domain.service.BurgerService;
import io.swagger.annotations.ApiOperation;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class responsible for mapping all RESTful endpoints related to the
 * {@link Burger} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/burgers")
public class BurgerController implements Serializable {

    private final BurgerService burgerService;

    @Autowired
    public BurgerController(BurgerService burgerService) {
        this.burgerService = burgerService;
    }

    /**
     * Finds all {@link Burger}.
     *
     * @return a {@link ResponseEntity} with the list of all {@link Burger}.
     */
    @GetMapping(path = "")
    @ApiOperation(value = "Returns a list with all burgers.",
            response = Burger[].class,
            produces = "application/json",
            httpMethod = "GET",
            code = 200)
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.burgerService.findAll(), HttpStatus.OK);
    }

    /**
     * Finds an {@link Burger} by its identifier.
     *
     * @param id The {@link Burger} identifier.
     * @return a {@link ResponseEntity} with the {@link Burger} entity.
     */
    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Returns a burger by a given identifier.",
            response = Burger.class,
            produces = "application/json",
            httpMethod = "GET",
            code = 200)
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Burger burger = this.burgerService.findById(id);
        if (burger == null) {
            throw new ResourceNotFoundException(id);
        }
        return new ResponseEntity<>(burger, HttpStatus.OK);
    }
}
