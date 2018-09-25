package com.burgerly.domain.service.impl;

import com.burgerly.application.exception.EntityNotExistsException;
import com.burgerly.domain.model.Burger;
import com.burgerly.domain.model.BurgerIngredient;
import com.burgerly.domain.model.Cart;
import com.burgerly.domain.model.CartBurger;
import com.burgerly.domain.model.CartBurgerIngredient;
import com.burgerly.domain.model.Ingredient;
import com.burgerly.domain.service.BurgerService;
import com.burgerly.domain.service.CartBurgerService;
import com.burgerly.domain.service.CartService;
import com.burgerly.infra.BurgerIngredientRepository;
import com.burgerly.infra.CartBurgerIngredientRepository;
import com.burgerly.infra.CartBurgerRepository;
import com.burgerly.infra.IngredientRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class responsible for all service cases related to the {@link CartBurger}
 * entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
@Service
public class CartBurgerServiceImpl implements CartBurgerService {

    private final CartBurgerRepository cartBurgerRepository;
    private final CartBurgerIngredientRepository cartBurgerIngredientRepository;
    private final BurgerIngredientRepository burgerIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final CartService cartService;
    private final BurgerService burgerService;

    @Autowired
    public CartBurgerServiceImpl(CartBurgerRepository cartBurgerRepository,
            CartBurgerIngredientRepository cartBurgerIngredientRepository,
            BurgerIngredientRepository burgerIngredientRepository,
            IngredientRepository ingredientRepository,
            CartService cartService,
            BurgerService burgerService) {
        this.cartBurgerRepository = cartBurgerRepository;
        this.cartBurgerIngredientRepository = cartBurgerIngredientRepository;
        this.burgerIngredientRepository = burgerIngredientRepository;
        this.ingredientRepository = ingredientRepository;
        this.cartService = cartService;
        this.burgerService = burgerService;
    }

    @Override
    @Transactional
    public CartBurger save(CartBurger cartBurger) {

        Cart cart = this.cartService.findById(cartBurger.getCart().getId());
        if (cart == null) {
            throw new EntityNotExistsException("Cart entity not found. ID: " + cartBurger.getId());
        }

        Burger burger = this.burgerService.findById(cartBurger.getBurger().getId());
        if (burger == null) {
            throw new EntityNotExistsException("Burger entity not found. ID: " + cartBurger.getId());
        }

        BigDecimal price = new BigDecimal("0.00");
        List<CartBurgerIngredient> cartBurgerIngredients = new ArrayList<>();
        Collection<BurgerIngredient> burgerIngredients = this.burgerIngredientRepository.findByBurger(cartBurger.getBurger());
        for (BurgerIngredient burgerIngredient : burgerIngredients) {
            price = price.add(burgerIngredient.getIngredient().getPrice());
            cartBurgerIngredients.add(new CartBurgerIngredient(cartBurger, burgerIngredient.getIngredient()));
        }

        cartBurger.setPrice(price);

        cartBurger = this.cartBurgerRepository.save(cartBurger);

        this.cartBurgerIngredientRepository.save(cartBurgerIngredients);

        cartBurger.setCart(this.cartService.recalculatePrice(cartBurger.getCart()));

        return cartBurger;
    }

    @Override
    @Transactional
    public void delete(CartBurger cartBurger) {
        this.cartBurgerRepository.delete(cartBurger);
        this.cartService.recalculatePrice(cartBurger.getCart());
    }

    @Override
    public CartBurger findById(Long id) {
        return this.cartBurgerRepository.findOne(id);
    }

    @Override
    @Transactional
    public CartBurger recalculatePrice(CartBurger cartBurger) {

        cartBurger = this.findById(cartBurger.getId());

        Collection<CartBurgerIngredient> cartBurgerIngredients = new ArrayList<>();
        BigDecimal price = new BigDecimal("0.00");
        cartBurgerIngredients = this.cartBurgerIngredientRepository.findByCartBurger(cartBurger);

        boolean thereIsLettuce = false;
        boolean thereIsBacon = false;
        int meatHamburgerCount = 0;
        int cheeseCount = 0;
        for (CartBurgerIngredient cartBurgerIngredient : cartBurgerIngredients) {

            if (cartBurgerIngredient.getIngredient().getId().equals(1l)) {
                thereIsLettuce = true;
            }

            if (cartBurgerIngredient.getIngredient().getId().equals(2l)) {
                thereIsBacon = true;
            }

            if (cartBurgerIngredient.getIngredient().getId().equals(3l)) {
                meatHamburgerCount++;
            }

            if (cartBurgerIngredient.getIngredient().getId().equals(5l)) {
                cheeseCount++;
            }

            Ingredient ingredient = this.ingredientRepository.findOne(cartBurgerIngredient.getIngredient().getId());
            price = price.add(ingredient.getPrice());
        }

        cartBurger.setPrice(this.calculateOffers(price,
                thereIsLettuce,
                thereIsBacon,
                meatHamburgerCount,
                cheeseCount));

        cartBurger = this.cartBurgerRepository.save(cartBurger);

        this.cartService.recalculatePrice(cartBurger.getCart());
        return cartBurger;
    }

    /**
     * Calculates all burger offers.
     *
     * @param thereIsLettuce a {@literal boolean}.
     * @param thereIsBacon a {@literal boolean}.
     * @param meatHamburgerCount a {@literal int}.
     * @param cheeseCount a {@literal int}.
     */
    private BigDecimal calculateOffers(BigDecimal currentPrice,
            boolean thereIsLettuce,
            boolean thereIsBacon,
            int meatHamburgerCount,
            int cheeseCount) {

        if (thereIsLettuce && !thereIsBacon) {
            currentPrice = this.calculateLightOffer(currentPrice);
        }

        if (meatHamburgerCount > 2) {
            currentPrice = this.calculateALotOfMeatOffer(currentPrice, meatHamburgerCount);
        }

        if (cheeseCount > 2) {
            currentPrice = this.calculateALotOfCheeseOffer(currentPrice, cheeseCount);
        }

        return currentPrice;
    }

    /**
     * Calculates the "Light" offer. This offer takes off 10 percent of the
     * burger price.
     *
     * @param price The actual burger price.
     * @return the new burger price.
     */
    private BigDecimal calculateLightOffer(BigDecimal price) {
        return price.subtract(price.multiply(new BigDecimal("0.10")));
    }

    /**
     * Calculates the "A lot of Meat" offer. Every 3 hamburgers, one aren't
     * charged.
     *
     * @param price The actual burger price.
     * @return the new burger price.
     */
    private BigDecimal calculateALotOfMeatOffer(BigDecimal price, int oldMeatHamburgerCount) {

        int newMeatHamburgerCount = oldMeatHamburgerCount;
        for (int i = 1; i <= oldMeatHamburgerCount; i++) {
            if (i % 3 == 0) {
                newMeatHamburgerCount = newMeatHamburgerCount - 1;
            }
        }

        Ingredient meatHamburger = this.ingredientRepository.findByDescription("Hambúrger de carne");
        BigDecimal oldMeatHamburgerPrice = meatHamburger.getPrice().multiply(new BigDecimal(oldMeatHamburgerCount));
        BigDecimal newMeatHamburgerPrice = meatHamburger.getPrice().multiply(new BigDecimal(newMeatHamburgerCount));
        BigDecimal aLotOfMeatOfferPrice = oldMeatHamburgerPrice.subtract(newMeatHamburgerPrice);

        return price.subtract(aLotOfMeatOfferPrice);
    }

    /**
     * Calculates the "A lot of Cheese" offer. Every 3 cheeses, one aren't
     * charged.
     *
     * @param price The actual burger price.
     * @return the new burger price.
     */
    private BigDecimal calculateALotOfCheeseOffer(BigDecimal price, int oldCheeseCount) {

        int newCheeseCount = oldCheeseCount;
        for (int i = 1; i <= oldCheeseCount; i++) {
            if (i % 3 == 0) {
                newCheeseCount = newCheeseCount - 1;
            }
        }

        Ingredient cheese = this.ingredientRepository.findByDescription("Queijo");
        BigDecimal oldCheesePrice = cheese.getPrice().multiply(new BigDecimal(oldCheeseCount));
        BigDecimal newCheesePrice = cheese.getPrice().multiply(new BigDecimal(newCheeseCount));
        BigDecimal aLotOfCheeseOfferPrice = oldCheesePrice.subtract(newCheesePrice);

        return price.subtract(aLotOfCheeseOfferPrice);
    }
}
