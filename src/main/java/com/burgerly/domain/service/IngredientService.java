package com.burgerly.domain.service;

import com.burgerly.domain.model.Ingredient;
import java.util.Collection;

/**
 * Interface responsible for all service cases related to the {@link Ingredient}
 * entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
public interface IngredientService {

    /**
     * Finds all {@link Ingredient} on database.
     *
     * @return a list with {@link Ingredient} entities.
     */
    Collection<Ingredient> findAll();

    /**
     * Finds a {@link Ingredient} on database by a given identifier.
     *
     * @param id A {@link Ingredient} identifier.
     * @return a {@link Ingredient} entity.
     */
    Ingredient findById(Long id);
}
