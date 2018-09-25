package com.burgerly.application.controller;

import com.burgerly.application.exception.ResourceNotFoundException;
import com.burgerly.domain.model.Cart;
import com.burgerly.domain.model.CartBurger;
import com.burgerly.domain.service.CartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class responsible for mapping all RESTful endpoints related to the
 * {@link Cart} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * Finds all {@link Cart}.
     *
     * @return a {@link ResponseEntity} with the list of all {@link Cart}.
     */
    @GetMapping(path = "")
    @ApiOperation(value = "Returns a list with all carts.",
            response = Cart[].class,
            httpMethod = "GET",
            code = 200)
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.cartService.findAll(), HttpStatus.OK);
    }

    /**
     * Finds an {@link Cart} by its identifier.
     *
     * @param id A {@link Cart} identifier.
     * @return a {@link ResponseEntity} with the {@link Cart} entity.
     */
    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Returns a cart by a given identifier.",
            response = Cart.class,
            httpMethod = "GET",
            code = 200)
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Cart cart = this.cartService.findById(id);
        if (cart == null) {
            throw new ResourceNotFoundException("Resource not found. ID: " + id);
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    /**
     * Creates a new {@link Cart}.
     *
     * @param cart A {@link Cart} entity.
     * @return the new {@link Cart} on response.
     */
    @PostMapping(path = "")
    @ApiOperation(value = "Creates a new cart.",
            response = Cart.class,
            httpMethod = "POST",
            code = 201)
    public ResponseEntity<?> save(@RequestBody Cart cart) {
        return new ResponseEntity<>(this.cartService.save(cart), HttpStatus.CREATED);
    }

    /**
     * Deletes {@link Cart}.
     *
     * @param id A {@link CartBurger} identifier.
     * @return a {@link ResponseEntity}.
     */
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deletes a cart.",
            httpMethod = "DELETE",
            code = 200)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Cart cart = this.cartService.findById(id);
        if (cart == null) {
            throw new ResourceNotFoundException("Resource not found. ID: " + id);
        }
        this.cartService.delete(cart);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
