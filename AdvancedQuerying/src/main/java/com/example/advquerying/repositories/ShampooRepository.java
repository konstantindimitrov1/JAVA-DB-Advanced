package com.example.advquerying.repositories;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    List<Shampoo> findAllBySizeOrderById(Size size);

    List<Shampoo> findAllBySizeOrLabel_IdOrderByPrice(Size size, Long labelId);

    List<Shampoo> findAllByPriceGreaterThanEqualOrderByPriceDesc(BigDecimal price);

    int countAllByPriceIsLessThan(BigDecimal price);

    @Query("select s from Shampoo s join s.ingredients i where i.name in :ingredients")
    List<Shampoo> getShampoosWithGivenIngredients(@Param(value = "ingredients") Set<String> ingredientsNames);

    @Query("select s from Shampoo s where size(s.ingredients) < :count")
    List<Shampoo> getShampoosByIngredientsLessThan(@Param(value = "count") int count);
}
