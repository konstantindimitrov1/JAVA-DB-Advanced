package com.example.advquerying;

import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import com.example.advquerying.entities.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
public class CommandRunner implements CommandLineRunner {

    private final ShampooService shampooService;

    private final IngredientService ingredientService;

    @Autowired
    public CommandRunner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {

        // the exercise methods are with hardcoded values

        System.out.println("Select exercise from 1 to 11:");

        try {
            Scanner scanner = new Scanner(System.in);
            int exNumber = Integer.parseInt(scanner.nextLine());

            switch (exNumber) {
                case 1 -> exercise1();
                case 2 -> exercise2();
                case 3 -> exercise3();
                case 4 -> exercise4();
                case 5 -> exercise5();
                case 6 -> exercise6();
                case 7 -> exercise7();
                case 8 -> exercise8();
                case 9 -> exercise9();
                case 10 -> exercise10();
                case 11 -> exercise11();
                default -> System.out.println("Please enter valid number from 1 to 11 next time!");
            }
        } catch (Exception e) {
            System.out.println("Try again with valid exercise number!");
        }
    }

    private void exercise11() {
        ingredientService.increasePriceOfGivenIngredients(List.of("Apple", "Nettle"));
    }

    private void exercise10() {
        ingredientService.increasePriceOfAllIngredientsByTenPercent();
    }

    private void exercise9() {
        ingredientService.deleteIngredientsByGivenName("Lavender");
    }

    private void exercise8() {
        shampooService.printShampoosWithIngredientsLessThan(2);
    }

    private void exercise7() {
        shampooService.printShampoosWithGivenIngredients(Set.of("Berry", "Mineral-Collagen"));
    }

    private void exercise6() {
        shampooService.printShampoosCountWithPriceLessThan(8.5);
    }

    private void exercise5() {
        ingredientService.printIngredientsInGivenList(List.of("Lavender", "Herbs", "Apple"));
    }

    private void exercise4() {
        ingredientService.printIngredientsThatStartWith("M");
    }

    private void exercise3() {
        shampooService.printAllShampoosWithPriceBiggerThan(5);
    }

    private void exercise2() {
        shampooService.printAllShampoosBySizeOrLabelId(Size.MEDIUM, 10L);
    }

    private void exercise1() {
        shampooService.printAllShampoosBySize(Size.MEDIUM);
    }
}
