package com.burgerly.domain.service;

import com.burgerly.domain.model.Burger;
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
 * {@link Burger}.
 *
 * @author Roberto Amadeu Neto
 * @since 26/09/2018
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BurgerServiceTests {

    @Autowired
    private BurgerService burgerService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldCalculateXBaconPrice() {
        BigDecimal price = this.burgerService.calculatePrice(this.burgerService.findById(1L));
        Assertions.assertThat(!price.equals(null));
        Assertions.assertThat(price.compareTo(new BigDecimal("6.50")) == 0);
    }

    @Test
    public void shouldCalculateXBurgerPrice() {
        BigDecimal price = this.burgerService.calculatePrice(this.burgerService.findById(2L));
        Assertions.assertThat(!price.equals(null));
        Assertions.assertThat(price.compareTo(new BigDecimal("4.50")) == 0);
    }

    @Test
    public void shouldCalculateXEggPrice() {
        BigDecimal price = this.burgerService.calculatePrice(this.burgerService.findById(3L));
        Assertions.assertThat(!price.equals(null));
        Assertions.assertThat(price.compareTo(new BigDecimal("5.30")) == 0);
    }

    @Test
    public void shouldCalculateXEggBaconPrice() {
        BigDecimal price = this.burgerService.calculatePrice(this.burgerService.findById(4L));
        Assertions.assertThat(!price.equals(null));
        Assertions.assertThat(price.compareTo(new BigDecimal("7.30")) == 0);
    }
}
