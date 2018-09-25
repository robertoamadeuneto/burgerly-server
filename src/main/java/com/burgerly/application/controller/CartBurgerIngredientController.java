package com.burgerly.application.controller;

import com.burgerly.application.exception.ResourceNotFoundException;
import com.burgerly.domain.model.CartBurger;
import com.burgerly.domain.model.CartBurgerIngredient;
import com.burgerly.domain.service.CartBurgerIngredientService;
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
@RequestMapping(path = "api/cart-burger-ingredients")
public class CartBurgerIngredientController {

    private final CartBurgerIngredientService cartBurgerIngredientService;

    @Autowired
    public CartBurgerIngredientController(CartBurgerIngredientService cartBurgerIngredientService) {
        this.cartBurgerIngredientService = cartBurgerIngredientService;
    }

    /**
     * Creates a new {@link CartBurgerIngredient}.
     *
     * @param cartBurgerIngredient A {@link CartBurgerIngredient} entity.
     * @return a {@link ResponseEntity} with the new
     * {@link CartBurgerIngredient}.
     */
    @PostMapping(path = "")
    @ApiOperation(value = "Creates a new cart burger ingredient.",
            response = CartBurgerIngredient.class,
            httpMethod = "POST",
            code = 201)
    public ResponseEntity<?> save(@RequestBody CartBurgerIngredient cartBurgerIngredient) {
        return new ResponseEntity<>(this.cartBurgerIngredientService.save(cartBurgerIngredient), HttpStatus.CREATED);
    }

    /**
     * Deletes {@link CartBurgerIngredient}.
     *
     * @param id A {@link CartBurgerIngredient} identifier.
     * @return a {@link ResponseEntity}.
     */
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deletes a cart burger ingredient.",
            httpMethod = "DELETE",
            code = 200)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        CartBurgerIngredient cartBurgerIngredient = this.cartBurgerIngredientService.findById(id);
        if (cartBurgerIngredient == null) {
            throw new ResourceNotFoundException("Resource not found. ID: " + id);
        }
        this.cartBurgerIngredientService.delete(cartBurgerIngredient);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
