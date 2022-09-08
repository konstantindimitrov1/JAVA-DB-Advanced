package com.example.advquerying.services;

import com.example.advquerying.entities.Size;

import java.util.Set;

public interface ShampooService {

    void printAllShampoosBySize (Size size);

    void printAllShampoosBySizeOrLabelId(Size size, Long labelId);

    void printAllShampoosWithPriceBiggerThan(double price);

    void printShampoosCountWithPriceLessThan(double price);

    void printShampoosWithGivenIngredients(Set<String> ingredientsNames);

    void printShampoosWithIngredientsLessThan(int count);
}
