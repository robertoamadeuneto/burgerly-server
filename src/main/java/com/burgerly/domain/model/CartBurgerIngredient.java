package com.burgerly.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Class responsible for mapping the CartBurgerIngredient entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
@Entity
@Table(name = "cart_burger_ingredient")
public class CartBurgerIngredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_burger_id", referencedColumnName = "id")
    private CartBurger cartBurger;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    private Ingredient ingredient;

    public CartBurgerIngredient() {
    }

    public CartBurgerIngredient(CartBurger cartBurger, Ingredient ingredient) {
        this.cartBurger = cartBurger;
        this.ingredient = ingredient;
    }

    public CartBurgerIngredient(Long id, CartBurger cartBurger, Ingredient ingredient) {
        this.id = id;
        this.cartBurger = cartBurger;
        this.ingredient = ingredient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CartBurger getCartBurger() {
        return cartBurger;
    }

    public void setCartBurger(CartBurger cartBurger) {
        this.cartBurger = cartBurger;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final CartBurgerIngredient other = (CartBurgerIngredient) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CartBurgerIngredient:" + this.id;
    }
}
