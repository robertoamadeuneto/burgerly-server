package com.burgerly.domain.service;

import com.burgerly.domain.model.Burger;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Interface responsible for all service cases related to the {@link Burger}
 * entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
public interface BurgerService {

    /**
     * Finds all {@link Burger} on database.
     *
     * @return a list with all {@link Burger} entities.
     */
    Collection<Burger> findAll();

    /**
     * Finds a {@link Burger} on database by a given identifier.
     *
     * @param id The {@link Burger} identifier.
     * @return a {@link Burger} entity.
     */
    Burger findById(Long id);

    /**
     * Calculates the price of a default burger based on its ingredients.
     *
     * @param burger A {@link Burger} entity.
     * @return a {@link BigDecimal} with the price.
     */
    BigDecimal calculatePrice(Burger burger);
}
