package com.burgerly.infra;

import com.burgerly.domain.model.Burger;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface responsible for mapping all repository cases related to the
 * {@link Burger} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
public interface BurgerRepository extends JpaRepository<Burger, Long> {

}
