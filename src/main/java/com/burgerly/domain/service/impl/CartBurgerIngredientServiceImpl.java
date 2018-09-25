package com.burgerly.domain.service.impl;

import com.burgerly.application.exception.EntityNotExistsException;
import com.burgerly.domain.model.CartBurger;
import com.burgerly.domain.model.CartBurgerIngredient;
import com.burgerly.domain.service.CartBurgerIngredientService;
import com.burgerly.domain.service.CartBurgerService;
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

    @Autowired
    public CartBurgerIngredientServiceImpl(CartBurgerIngredientRepository cartBurgerIngredientRepository,
            CartBurgerService cartBurgerService) {
        this.cartBurgerIngredientRepository = cartBurgerIngredientRepository;
        this.cartBurgerService = cartBurgerService;
    }

    @Override
    @Transactional
    public CartBurgerIngredient save(CartBurgerIngredient cartBurgerIngredient) {
        CartBurger cartBurger = this.cartBurgerService.findById(cartBurgerIngredient.getCartBurger().getId());
        if (cartBurger == null) {
            throw new EntityNotExistsException("Cart Burger entity not exists. ID: " + cartBurgerIngredient.getId());
        }
        cartBurgerIngredient = this.cartBurgerIngredientRepository.save(cartBurgerIngredient);
        this.cartBurgerService.recalculatePrice(cartBurgerIngredient.getCartBurger());
        return cartBurgerIngredient;
    }

    @Override
    @Transactional
    public void delete(CartBurgerIngredient cartBurgerIngredient) {
        this.cartBurgerIngredientRepository.delete(cartBurgerIngredient);
        this.cartBurgerService.recalculatePrice(cartBurgerIngredient.getCartBurger());
    }

    @Override
    public CartBurgerIngredient findById(Long id) {
        return this.cartBurgerIngredientRepository.findOne(id);
    }
}
