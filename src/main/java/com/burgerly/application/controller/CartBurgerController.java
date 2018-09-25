package com.burgerly.application.controller;

import com.burgerly.application.exception.ResourceNotFoundException;
import com.burgerly.domain.model.CartBurger;
import com.burgerly.domain.service.CartBurgerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class responsible for mapping all RESTful endpoints related to the
 * {@link CartBurger} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/cart-burgers")
public class CartBurgerController {

    private final CartBurgerService cartBurgerService;

    @Autowired
    public CartBurgerController(CartBurgerService cartBurgerService) {
        this.cartBurgerService = cartBurgerService;
    }

    /**
     * Creates a new {@link CartBurger}.
     *
     * @param cartBurger A {@link CartBurger} entity.
     * @return a {@link ResponseEntity} with the the new {@link CartBurger}.
     */
    @PostMapping(path = "")
    @ApiOperation(value = "Creates a new cart burger.",
            response = CartBurger.class,
            httpMethod = "POST",
            code = 201)
    public ResponseEntity<?> save(@RequestBody CartBurger cartBurger) {
        return new ResponseEntity<>(this.cartBurgerService.save(cartBurger), HttpStatus.CREATED);
    }

    /**
     * Deletes {@link CartBurger}.
     *
     * @param id A {@link CartBurger} identifier.
     * @return a {@link ResponseEntity}.
     */
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deletes a cart burger.",
            httpMethod = "DELETE",
            code = 200)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        CartBurger cartBurger = this.cartBurgerService.findById(id);
        if (cartBurger == null) {
            throw new ResourceNotFoundException("Resource not found. ID: " + id);
        }
        this.cartBurgerService.delete(cartBurger);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
