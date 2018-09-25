package com.burgerly.domain.service.impl;

import com.burgerly.domain.model.Burger;
import com.burgerly.domain.model.BurgerIngredient;
import com.burgerly.domain.service.BurgerService;
import com.burgerly.infra.BurgerIngredientRepository;
import com.burgerly.infra.BurgerRepository;
import java.math.BigDecimal;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class responsible for all service cases related to the {@link Burger} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
@Service
public class BurgerServiceImpl implements BurgerService {

    private final BurgerRepository burgerRepository;
    private final BurgerIngredientRepository burgerIngredientRepository;

    @Autowired
    public BurgerServiceImpl(BurgerRepository burgerRepository,
            BurgerIngredientRepository burgerIngredientRepository) {
        this.burgerRepository = burgerRepository;
        this.burgerIngredientRepository = burgerIngredientRepository;
    }

    @Override
    public Collection<Burger> findAll() {
        Collection<Burger> burgers = this.burgerRepository.findAll();
        for (Burger burger : burgers) {
            burger.setPrice(this.calculatePrice(burger));
        }
        return burgers;
    }

    @Override
    public Burger findById(Long id) {
        return this.burgerRepository.findOne(id);
    }

    /**
     * Calculates the price of a default burger based on its ingredients.
     *
     * @param burger A {@link Burger} entity.
     * @return a {@link BigDecimal} with the price.
     */
    @Override
    @Transactional
    public BigDecimal calculatePrice(Burger burger) {
        BigDecimal price = new BigDecimal("0.00");
        Collection<BurgerIngredient> burgerIngredients = this.burgerIngredientRepository.findByBurger(burger);
        for (BurgerIngredient burgerIngredient : burgerIngredients) {
            price = price.add(burgerIngredient.getIngredient().getPrice());
        }

        return price;
    }
}
