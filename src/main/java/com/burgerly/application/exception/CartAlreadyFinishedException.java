package com.burgerly.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when trying to modify an already finished cart.
 *
 * @author Roberto Amadeu Neto
 * @since 25/09/2018
 * @version 1.0
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CartAlreadyFinishedException extends RuntimeException {

    public CartAlreadyFinishedException(Long cartId) {
        super("Cart already finished. ID: " + cartId);
    }
}
