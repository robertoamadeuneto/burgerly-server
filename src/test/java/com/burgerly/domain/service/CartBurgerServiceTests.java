package com.burgerly.domain.service;

import com.burgerly.domain.model.Burger;
import com.burgerly.domain.model.Cart;
import com.burgerly.domain.model.CartBurger;
import com.burgerly.domain.model.CartBurgerIngredient;
import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Test class responsible for service cases implementations related to
 * {@link CartBurger}.
 *
 * @author Roberto Amadeu Neto
 * @since 26/09/2018
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartBurgerServiceTests {

    @Autowired
    private BurgerService burgerService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartBurgerService cartBurgerService;

    @Autowired
    private CartBurgerIngredientService cartBurgerIngredientService;

//    @Autowired
//    private JdbcTemplate jdbcTemplate;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

//    private static final String CREATE_SCRIPT = "scripts/create.sql";
//    private static final String DROP_SCRIPT = "scripts/drop.sql";
//
//    @Before
//    public void before() throws SQLException {
//        ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), new ClassPathResource(CREATE_SCRIPT));
//    }
//
//    @After
//    public void after() throws SQLException {
//        ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), new ClassPathResource(DROP_SCRIPT));
//    }
    @Test
    public void shouldCalculateACustomBurgerPrice() {
        Cart cart = new Cart("Cart 01", new BigDecimal("0.00"));
        cart = this.cartService.save(cart);
        Assertions.assertThat(!cart.getId().equals(null));

        CartBurger cartBurger = new CartBurger();
        cartBurger.setCart(cart);
        Burger burger = this.burgerService.findById(1L);
        cartBurger.setBurger(burger);
        cartBurger = this.cartBurgerService.save(cartBurger);
        Assertions.assertThat(!cartBurger.getId().equals(null));

        CartBurgerIngredient cartBurgerIngredient = new CartBurgerIngredient(cartBurger, this.ingredientService.findById(5l));
        cartBurgerIngredient = this.cartBurgerIngredientService.save(cartBurgerIngredient);
        Assertions.assertThat(!cartBurgerIngredient.equals(null));

        cartBurger = this.cartBurgerService.recalculatePrice(cartBurger);
        Assertions.assertThat(!cartBurger.equals(null));
        Assertions.assertThat(cartBurger.getPrice().compareTo(new BigDecimal("7.50")) == 0);

    }

    @Test
    public void shouldCalculateLightOfferPrice() {
        Cart cart = new Cart("Cart 02", new BigDecimal("0.00"));
        cart = this.cartService.save(cart);
        Assertions.assertThat(!cart.getId().equals(null));

        CartBurger cartBurger = new CartBurger();
        cartBurger.setCart(cart);
        Burger burger = this.burgerService.findById(2L);
        cartBurger.setBurger(burger);
        cartBurger = this.cartBurgerService.save(cartBurger);
        Assertions.assertThat(!cartBurger.getId().equals(null));

        CartBurgerIngredient cartBurgerIngredient = new CartBurgerIngredient(cartBurger, this.ingredientService.findById(1l));
        cartBurgerIngredient = this.cartBurgerIngredientService.save(cartBurgerIngredient);
        Assertions.assertThat(!cartBurgerIngredient.equals(null));

        cartBurger = this.cartBurgerService.recalculatePrice(cartBurger);
        Assertions.assertThat(!cartBurger.equals(null));
        Assertions.assertThat(cartBurger.getPrice().compareTo(new BigDecimal("4.41")) == 0);
    }

    @Test
    public void shouldCalculateALotOfMeatPrice() {
        Cart cart = new Cart("Cart 03", new BigDecimal("0.00"));
        cart = this.cartService.save(cart);
        Assertions.assertThat(!cart.getId().equals(null));

        CartBurger cartBurger = new CartBurger();
        cartBurger.setCart(cart);
        Burger burger = this.burgerService.findById(1L);
        cartBurger.setBurger(burger);
        cartBurger = this.cartBurgerService.save(cartBurger);
        Assertions.assertThat(!cartBurger.getId().equals(null));

        CartBurgerIngredient cartBurgerIngredient1 = new CartBurgerIngredient(cartBurger, this.ingredientService.findById(3l));
        cartBurgerIngredient1 = this.cartBurgerIngredientService.save(cartBurgerIngredient1);
        Assertions.assertThat(!cartBurgerIngredient1.equals(null));

        CartBurgerIngredient cartBurgerIngredient2 = new CartBurgerIngredient(cartBurger, this.ingredientService.findById(3l));
        cartBurgerIngredient2 = this.cartBurgerIngredientService.save(cartBurgerIngredient2);
        Assertions.assertThat(!cartBurgerIngredient2.equals(null));

        cartBurger = this.cartBurgerService.recalculatePrice(cartBurger);
        Assertions.assertThat(!cartBurger.equals(null));
        Assertions.assertThat(cartBurger.getPrice().compareTo(new BigDecimal("9.50")) == 0);
    }

    @Test
    public void shouldCalculateALotOfEggPrice() {
        Cart cart = new Cart("Cart 04", new BigDecimal("0.00"));
        cart = this.cartService.save(cart);
        Assertions.assertThat(!cart.getId().equals(null));

        CartBurger cartBurger = new CartBurger();
        cartBurger.setCart(cart);
        Burger burger = this.burgerService.findById(1L);
        cartBurger.setBurger(burger);
        cartBurger = this.cartBurgerService.save(cartBurger);
        Assertions.assertThat(!cartBurger.getId().equals(null));

        CartBurgerIngredient cartBurgerIngredient1 = new CartBurgerIngredient(cartBurger, this.ingredientService.findById(5l));
        cartBurgerIngredient1 = this.cartBurgerIngredientService.save(cartBurgerIngredient1);
        Assertions.assertThat(!cartBurgerIngredient1.equals(null));

        CartBurgerIngredient cartBurgerIngredient2 = new CartBurgerIngredient(cartBurger, this.ingredientService.findById(5l));
        cartBurgerIngredient2 = this.cartBurgerIngredientService.save(cartBurgerIngredient2);
        Assertions.assertThat(!cartBurgerIngredient2.equals(null));

        cartBurger = this.cartBurgerService.recalculatePrice(cartBurger);
        Assertions.assertThat(!cartBurger.equals(null));
        Assertions.assertThat(cartBurger.getPrice().compareTo(new BigDecimal("8.00")) == 0);
    }
}
