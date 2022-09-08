package com.example.advquerying.services;

import com.example.advquerying.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void printIngredientsThatStartWith(String str) {
        ingredientRepository.findAllByNameStartsWith(str)
                .forEach(i -> System.out.println(i.getName()));
    }

    @Override
    public void printIngredientsInGivenList(Collection<String> ingredients) {
        ingredientRepository.findAllByNameInOrderByPrice(ingredients)
                .forEach(i -> System.out.println(i.getName()));
    }

    @Override
    @Transactional
    public void deleteIngredientsByGivenName(String ingredientName) {
        ingredientRepository.deleteIngredientByGivenName(ingredientName);
    }

    @Override
    @Transactional
    public void increasePriceOfAllIngredientsByTenPercent() {
        ingredientRepository.increasePriceOfAllIngredientsByTenPercent();
    }

    @Override
    @Transactional
    public void increasePriceOfGivenIngredients(List<String> ingredients) {
        ingredientRepository.increasePriceOfGivenIngredients(ingredients);
    }
}
