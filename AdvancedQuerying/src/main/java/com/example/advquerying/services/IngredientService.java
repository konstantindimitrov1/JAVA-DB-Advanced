package com.example.advquerying.services;

import java.util.Collection;
import java.util.List;

public interface IngredientService {

    void printIngredientsThatStartWith(String str);

    void printIngredientsInGivenList(Collection<String> ingredients);

    void deleteIngredientsByGivenName(String ingredientName);

    void increasePriceOfAllIngredientsByTenPercent();

    void increasePriceOfGivenIngredients(List<String> apple);
}
