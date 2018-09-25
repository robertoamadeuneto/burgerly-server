package com.burgerly.infra;

import com.burgerly.domain.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface responsible for mapping all repository cases related to the
 * {@link Cart} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
public interface CartRepository extends JpaRepository<Cart, Long> {

}
