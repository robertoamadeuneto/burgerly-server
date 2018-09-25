package com.burgerly.domain.service;

import com.burgerly.domain.model.Cart;
import java.util.Collection;

/**
 * Interface responsible for all service cases related to the {@link Cart}
 * entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
public interface CartService {

    /**
     * Saves a new {@link Cart} on database.
     *
     * @param cart A {@link Cart} entity.
     * @return a {@link Cart} entity.
     */
    Cart save(Cart cart);

    /**
     * Deletes a {@link Cart} from database.
     *
     * @param cart A {@link Cart} entity.
     */
    void delete(Cart cart);

    /**
     * Finds all {@link Cart} on database.
     *
     * @return a list with {@link Cart} entities.
     */
    Collection<Cart> findAll();

    /**
     * Finds a {@link Cart} on database by a given identifier.
     *
     * @param id A {@link Cart} identifier.
     * @return a {@link Cart} entity.
     */
    Cart findById(Long id);

    /**
     * Recalculates the {@link Cart} price.
     *
     * @param cart A {@link Cart} entity.
     * @return a {@link Cart} entity.
     */
    Cart recalculatePrice(Cart cart);
}
