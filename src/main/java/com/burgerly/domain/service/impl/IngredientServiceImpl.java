package com.burgerly.domain.service.impl;

import com.burgerly.domain.model.Ingredient;
import com.burgerly.domain.service.IngredientService;
import com.burgerly.infra.IngredientRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class responsible for all service cases related to the {@link Ingredient}
 * entity.
 *
 * @author Roberto Amadeu Neto
 * @since 24/09/2018
 * @version 1.0
 */
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Collection<Ingredient> findAll() {
        return this.ingredientRepository.findAll();
    }

    @Override
    public Ingredient findById(Long id) {
        return this.ingredientRepository.findOne(id);
    }
}
