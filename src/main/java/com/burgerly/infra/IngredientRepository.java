package com.burgerly.infra;

import com.burgerly.domain.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface responsible for mapping all repository cases related to the
 * {@link Ingredient} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    /**
     * Finds an {@link Ingredient} by a given description.
     *
     * @param description The {@link Ingredient} description.
     * @return a {@link Ingredient} entity.
     */
    Ingredient findByDescription(String description);
}
