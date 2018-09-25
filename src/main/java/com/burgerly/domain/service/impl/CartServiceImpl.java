package com.burgerly.domain.service.impl;

import com.burgerly.application.exception.CartAlreadyFinishedException;
import com.burgerly.domain.model.Cart;
import com.burgerly.domain.model.CartBurger;
import com.burgerly.domain.service.CartService;
import com.burgerly.infra.CartBurgerRepository;
import com.burgerly.infra.CartRepository;
import java.math.BigDecimal;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class responsible for all service cases related to the {@link Cart} entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartBurgerRepository cartBurgerRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository,
            CartBurgerRepository cartBurgerRepository) {
        this.cartRepository = cartRepository;
        this.cartBurgerRepository = cartBurgerRepository;
    }

    @Override
    @Transactional
    public Cart save(Cart cart) {
        if (cart.getId() == null) {
            cart.setPrice(new BigDecimal("0.00"));
        }

        return this.cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void delete(Cart cart) {
        this.cartRepository.delete(cart);
    }

    @Override
    public Collection<Cart> findAll() {
        return this.cartRepository.findAll();
    }

    @Override
    public Cart findById(Long id) {
        return this.cartRepository.findOne(id);
    }

    @Override
    @Transactional
    public Cart recalculatePrice(Cart cart) {
        cart = this.findById(cart.getId());
        Collection<CartBurger> cartBurgers = this.cartBurgerRepository.findByCart(cart);
        BigDecimal price = new BigDecimal("0.00");
        for (CartBurger cartBurger : cartBurgers) {
            price = price.add(cartBurger.getPrice());
        }
        cart.setPrice(price);
        return this.save(cart);
    }

    @Override
    @Transactional
    public Cart finish(Cart cart) {
        Cart dbCart = this.cartRepository.findOne(cart.getId());
        if (dbCart.getFinished() != null && dbCart.getFinished()) {
            throw new CartAlreadyFinishedException(dbCart.getId());
        }
        return this.cartRepository.save(cart);
    }
}
