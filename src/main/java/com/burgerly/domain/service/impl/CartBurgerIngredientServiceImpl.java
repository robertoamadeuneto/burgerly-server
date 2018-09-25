package com.burgerly.domain.service.impl;

import com.burgerly.application.exception.CartAlreadyFinishedException;
import com.burgerly.application.exception.EntityNotExistsException;
import com.burgerly.domain.model.CartBurger;
import com.burgerly.domain.model.CartBurgerIngredient;
import com.burgerly.domain.service.CartBurgerIngredientService;
import com.burgerly.domain.service.CartBurgerService;
import com.burgerly.domain.service.CartService;
import com.burgerly.infra.CartBurgerIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class responsible for all service cases related to the
 * {@link CartBurgerIngredient} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
@Service
public class CartBurgerIngredientServiceImpl implements CartBurgerIngredientService {

    private final CartBurgerIngredientRepository cartBurgerIngredientRepository;
    private final CartBurgerService cartBurgerService;
    private final CartService cartService;

    @Autowired
    public CartBurgerIngredientServiceImpl(CartBurgerIngredientRepository cartBurgerIngredientRepository,
            CartBurgerService cartBurgerService,
            CartService cartService) {
        this.cartBurgerIngredientRepository = cartBurgerIngredientRepository;
        this.cartBurgerService = cartBurgerService;
        this.cartService = cartService;
    }

    @Override
    @Transactional
    public CartBurgerIngredient save(CartBurgerIngredient cartBurgerIngredient) {
        CartBurger cartBurger = this.cartBurgerService.findById(cartBurgerIngredient.getCartBurger().getId());
        if (cartBurger == null) {
            throw new EntityNotExistsException(cartBurgerIngredient.getId());
        } else if (cartBurger.getCart().getFinished() != null && cartBurger.getCart().getFinished()) {
            throw new CartAlreadyFinishedException(cartBurger.getCart().getId());
        }

        cartBurgerIngredient = this.cartBurgerIngredientRepository.save(cartBurgerIngredient);
        this.cartBurgerService.recalculatePrice(cartBurgerIngredient.getCartBurger());
        return cartBurgerIngredient;
    }

    @Override
    @Transactional
    public void delete(CartBurgerIngredient cartBurgerIngredient) {
        if (cartBurgerIngredient.getCartBurger().getCart().getFinished() != null
                && cartBurgerIngredient.getCartBurger().getCart().getFinished()) {
            throw new CartAlreadyFinishedException(cartBurgerIngredient.getCartBurger().getCart().getId());
        }
        this.cartBurgerIngredientRepository.delete(cartBurgerIngredient);
        this.cartBurgerService.recalculatePrice(cartBurgerIngredient.getCartBurger());
    }

    @Override
    public CartBurgerIngredient findById(Long id) {
        return this.cartBurgerIngredientRepository.findOne(id);
    }
}
