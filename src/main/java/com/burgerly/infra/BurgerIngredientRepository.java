package com.burgerly.infra;

import com.burgerly.domain.model.Burger;
import com.burgerly.domain.model.BurgerIngredient;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface responsible for mapping all repository cases related to the
 * {@link Burger} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
public interface BurgerIngredientRepository extends JpaRepository<BurgerIngredient, Long> {

    /**
     * Lists all {@link BurgerIngredient} by a given {@link Burger}.
     *
     * @param burger A {@link Burger} entity.
     * @return a list of {@link BurgerIngredient}.
     */
    Collection<BurgerIngredient> findByBurger(Burger burger);
}
