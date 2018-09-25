package com.burgerly.domain.service;

import com.burgerly.domain.model.CartBurger;

/**
 * Interface responsible for all service cases related to the {@link CartBurger}
 * entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
public interface CartBurgerService {

    /**
     * Saves a new {@link CartBurger} on database.
     *
     * @param cartBurger A {@link CartBurger} entity.
     * @return a {@link CartBurger} entity.
     */
    CartBurger save(CartBurger cartBurger);

    /**
     * Deletes a {@link CartBurger} from database.
     *
     * @param cartBurger A {@link CartBurger} entity.
     */
    void delete(CartBurger cartBurger);

    /**
     * Finds a {@link CartBurger} on database by a given identifier.
     *
     * @param id A {@link CartBurger} identifier.
     * @return a {@link CartBurger} entity.
     */
    CartBurger findById(Long id);

    /**
     * Recalculates the {@link CartBurger} price.
     *
     * @param cartBurger A {@link CartBurger} entity.
     * @return a {@link CartBurger} entity.
     */
    CartBurger recalculatePrice(CartBurger cartBurger);
}
