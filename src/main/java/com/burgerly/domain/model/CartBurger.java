package com.burgerly.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Class responsible for mapping the CartBurger entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
@Entity
@Table(name = "cart_burger")
public class CartBurger implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "burger_id", referencedColumnName = "id")
    private Burger burger;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "cartBurger", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Collection<CartBurgerIngredient> cartBurgerIngredients;

    public CartBurger() {
    }

    public CartBurger(Cart cart, Burger burger, BigDecimal price, Collection<CartBurgerIngredient> cartBurgerIngredients) {
        this.cart = cart;
        this.burger = burger;
        this.price = price;
        this.cartBurgerIngredients = cartBurgerIngredients;
    }

    public CartBurger(Long id, Cart cart, Burger burger, BigDecimal price, Collection<CartBurgerIngredient> cartBurgerIngredients) {
        this.id = id;
        this.cart = cart;
        this.burger = burger;
        this.price = price;
        this.cartBurgerIngredients = cartBurgerIngredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Burger getBurger() {
        return burger;
    }

    public void setBurger(Burger burger) {
        this.burger = burger;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Collection<CartBurgerIngredient> getCartBurgerIngredients() {
        return cartBurgerIngredients;
    }

    public void setCartBurgerIngredients(Collection<CartBurgerIngredient> cartBurgerIngredients) {
        this.cartBurgerIngredients = cartBurgerIngredients;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CartBurger other = (CartBurger) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CartBurger: " + this.id;
    }
}
