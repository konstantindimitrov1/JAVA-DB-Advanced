package com.example.advquerying.services;

import com.example.advquerying.repositories.ShampooRepository;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;

    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public void printAllShampoosBySize (Size size) {
        shampooRepository.findAllBySizeOrderById(size)
                .forEach(ShampooServiceImpl::shampooPrintPattern);
    }

    @Override
    public void printAllShampoosBySizeOrLabelId(Size size, Long labelId) {
        shampooRepository.findAllBySizeOrLabel_IdOrderByPrice(size, labelId)
                .forEach(ShampooServiceImpl::shampooPrintPattern);
    }

    @Override
    public void printAllShampoosWithPriceBiggerThan(double price) {
        BigDecimal bigDecimal = BigDecimal.valueOf(price);
        shampooRepository.findAllByPriceGreaterThanEqualOrderByPriceDesc(bigDecimal)
                .forEach(ShampooServiceImpl::shampooPrintPattern);
    }

    @Override
    public void printShampoosCountWithPriceLessThan(double price) {
        System.out.println(shampooRepository.countAllByPriceIsLessThan(BigDecimal.valueOf(price)));
    }

    @Override
    public void printShampoosWithGivenIngredients(Set<String> ingredientsNames) {
        shampooRepository.getShampoosWithGivenIngredients(ingredientsNames)
                .forEach(s -> System.out.println(s.getBrand()));
    }

    @Override
    public void printShampoosWithIngredientsLessThan(int count) {
        shampooRepository.getShampoosByIngredientsLessThan(count)
                .forEach(s -> System.out.println(s.getBrand()));
    }

    private static void shampooPrintPattern(Shampoo s) {
        System.out.printf("%s %s %.2flv.\n", s.getBrand(),
                s.getSize().name(), s.getPrice());
    }
}
