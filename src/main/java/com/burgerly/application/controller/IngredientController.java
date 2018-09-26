package com.burgerly.application.controller;

import com.burgerly.application.exception.ResourceNotFoundException;
import com.burgerly.domain.model.Ingredient;
import com.burgerly.domain.service.IngredientService;
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
 * {@link Ingredient} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/ingredients")
public class IngredientController implements Serializable {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    /**
     * Lists all {@link Ingredient}.
     *
     * @return a {@link ResponseEntity} with the list of all {@link Ingredient}.
     */
    @GetMapping(path = "")
    @ApiOperation(value = "Returns a list with all ingredients.",
            response = Ingredient[].class,
            produces = "application/json",
            httpMethod = "GET",
            code = 200)
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.ingredientService.findAll(), HttpStatus.OK);
    }

    /**
     * Finds an {@link Ingredient} by its identifier.
     *
     * @param id A {@link Ingredient} identifier.
     * @return a {@link ResponseEntity} with the {@link Ingredient} entity.
     */
    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Returns a ingredient by a given identifier.",
            response = Ingredient.class,
            produces = "application/json",
            httpMethod = "GET",
            code = 200)
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Ingredient ingredient = this.ingredientService.findById(id);
        if (ingredient == null) {
            throw new ResourceNotFoundException(id);
        }
        return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }
}
