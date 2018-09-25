package com.burgerly.infra;

import com.burgerly.domain.model.CartBurger;
import com.burgerly.domain.model.CartBurgerIngredient;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface responsible for mapping all repository cases related to the
 * {@link CartBurgerIngredient} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
public interface CartBurgerIngredientRepository extends JpaRepository<CartBurgerIngredient, Long> {

    /**
     * Finds all {@link CartBurgerIngredient} by a given {@link CartBurger}.
     *
     * @param cartBurger A {@link CartBurger} entity.
     * @return a list of {@link CartBurgerIngredient}.
     */
    Collection<CartBurgerIngredient> findByCartBurger(CartBurger cartBurger);
}
