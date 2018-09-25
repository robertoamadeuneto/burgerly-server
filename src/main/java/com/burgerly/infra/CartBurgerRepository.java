package com.burgerly.infra;

import com.burgerly.domain.model.Cart;
import com.burgerly.domain.model.CartBurger;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface responsible for mapping all repository cases related to the
 * {@link CartBurger} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
public interface CartBurgerRepository extends JpaRepository<CartBurger, Long> {

    /**
     * Finds a list of {@link CartBurger} by a given {@link Cart}.
     *
     * @param cart A {@link Cart} entity.
     * @return a list of {@link CartBurger}.
     */
    Collection<CartBurger> findByCart(Cart cart);
}
