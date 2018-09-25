package com.burgerly.domain.service;

import com.burgerly.domain.model.CartBurgerIngredient;

/**
 * Interface responsible for all service cases related to the
 * {@link CartBurgerIngredient} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
public interface CartBurgerIngredientService {

    /**
     * Saves a new {@link CartBurgerIngredient} on database.
     *
     * @param cartBurgerIngredient A {@link CartBurgerIngredient} entity.
     * @return a {@link CartBurgerIngredient} entity.
     */
    CartBurgerIngredient save(CartBurgerIngredient cartBurgerIngredient);

    /**
     * Deletes a {@link CartBurgerIngredient} from database.
     *
     * @param cartBurgerIngredient A {@link CartBurgerIngredient} entity.
     */
    void delete(CartBurgerIngredient cartBurgerIngredient);

    /**
     * Finds a {@link CartBurgerIngredient} on database by a given identifier.
     *
     * @param id A {@link CartBurgerIngredient} identifier.
     * @return a {@link CartBurgerIngredient} entity.
     */
    CartBurgerIngredient findById(Long id);
}
